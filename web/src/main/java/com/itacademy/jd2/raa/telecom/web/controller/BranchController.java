package com.itacademy.jd2.raa.telecom.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IBranch;
import com.itacademy.jd2.raa.telecom.dao.api.filter.BranchFilter;
import com.itacademy.jd2.raa.telecom.service.IBranchService;
import com.itacademy.jd2.raa.telecom.web.converter.BranchFromDTOConverter;
import com.itacademy.jd2.raa.telecom.web.converter.BranchToDTOConverter;
import com.itacademy.jd2.raa.telecom.web.dto.BranchDTO;
import com.itacademy.jd2.raa.telecom.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/branch")
public class BranchController extends AbstractController {
	@Autowired
	private IBranchService branchService;
	@Autowired
	private BranchToDTOConverter toDtoConverter;
	@Autowired
	private BranchFromDTOConverter fromDtoConverter;

	@Autowired
	private BranchController(IBranchService attributeService, BranchToDTOConverter toDtoConverter,
			BranchFromDTOConverter fromDtoConverter) {
		super();
		this.branchService = attributeService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {
		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final BranchFilter filter = new BranchFilter();
		prepareFilter(gridState, filter);

		final List<IBranch> entities = branchService.find(filter);
		List<BranchDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(branchService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("branch.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IBranch newEntity = branchService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("branch.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final BranchDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "branch.edit";
		} else {
			final IBranch entity = fromDtoConverter.apply(formModel);
			branchService.save(entity);
			return "redirect:/branch"; // generates 302 response with Location="/carsdealer/brand"
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		branchService.delete(id);
		return "redirect:/branch";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IBranch dbModel = branchService.get(id);
		final BranchDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("branch.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final BranchDTO dto = toDtoConverter.apply(branchService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("branch.edit", hashMap);
	}

}
