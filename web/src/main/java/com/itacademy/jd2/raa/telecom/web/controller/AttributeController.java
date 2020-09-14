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

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttribute;
import com.itacademy.jd2.raa.telecom.dao.api.filter.AttributeFilter;
import com.itacademy.jd2.raa.telecom.service.IAttributeService;
import com.itacademy.jd2.raa.telecom.web.converter.AttributeFromDTOConverter;
import com.itacademy.jd2.raa.telecom.web.converter.AttributeToDTOConverter;
import com.itacademy.jd2.raa.telecom.web.dto.AttributeDTO;
import com.itacademy.jd2.raa.telecom.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/attribute")
public class AttributeController extends AbstractController {
	@Autowired
	private IAttributeService attributeService;
	@Autowired
	private AttributeToDTOConverter toDtoConverter;
	@Autowired
	private AttributeFromDTOConverter fromDtoConverter;

	@Autowired
	private AttributeController(IAttributeService attributeService, AttributeToDTOConverter toDtoConverter,
			AttributeFromDTOConverter fromDtoConverter) {
		super();
		this.attributeService = attributeService;
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

		final AttributeFilter filter = new AttributeFilter();
		prepareFilter(gridState, filter);
		
		final List<IAttribute> entities = attributeService.find(filter);
		List<AttributeDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(attributeService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("attribute.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IAttribute newEntity = attributeService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("attribute.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final AttributeDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "attribute.edit";
		} else {
			final IAttribute entity = fromDtoConverter.apply(formModel);
			attributeService.save(entity);

			return "redirect:/attribute"; // generates 302 response with Location="/carsdealer/brand"
			
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		attributeService.delete(id);
		return "redirect:/attribute";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IAttribute dbModel = attributeService.get(id);
		final AttributeDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("attribute.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final AttributeDTO dto = toDtoConverter.apply(attributeService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("attribute.edit", hashMap);
	}

}
