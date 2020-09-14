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

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ISupport;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.dao.api.filter.SupportFilter;
import com.itacademy.jd2.raa.telecom.service.ISupportService;
import com.itacademy.jd2.raa.telecom.service.IUserAccountService;
import com.itacademy.jd2.raa.telecom.service.IUserCabinetService;
import com.itacademy.jd2.raa.telecom.web.converter.SupportFromDTOConverter;
import com.itacademy.jd2.raa.telecom.web.converter.SupportToDTOConverter;
import com.itacademy.jd2.raa.telecom.web.dto.SupportDTO;
import com.itacademy.jd2.raa.telecom.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/support")
public class SupportController extends AbstractController {

	@Autowired
	private ISupportService supportService;
	@Autowired
	private IUserAccountService userAccountService;
	@Autowired
	private IUserCabinetService userCabinetService;
	@Autowired
	private SupportToDTOConverter toDtoConverter;
	@Autowired
	private SupportFromDTOConverter fromDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final SupportFilter filter = new SupportFilter();

		prepareFilter(gridState, filter);
		gridState.setTotalCount(supportService.getCount(filter));

		filter.setFetchUserCabinet(true);
		filter.setFetchUserAccount(true);

		final List<ISupport> entities = supportService.find(filter);
		List<SupportDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("support.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new SupportDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("support.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final SupportDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("support.edit", hashMap);
		} else {
			final ISupport entity = fromDtoConverter.apply(formModel);
			entity.setStatus(false);
			supportService.save(entity);
			return "redirect:/support";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ISupport dbModel = supportService.getFullInfo(id);
		final SupportDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("support.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final SupportDTO dto = toDtoConverter.apply(supportService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("support.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		supportService.delete(id);
		return "redirect:/support";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IUserAccount> userAccount = userAccountService.getAll();

		final Map<Integer, String> userAccountMap = userAccount.stream()
				.collect(Collectors.toMap(IUserAccount::getId, IUserAccount::getEmail));
		hashMap.put("userAccountChoices", userAccountMap);

		final List<IUserCabinet> userCabinet = userCabinetService.getAll();

		final Map<Integer, Integer> userCabinetMap = userCabinet.stream()
				.collect(Collectors.toMap(IUserCabinet::getId, IUserCabinet::getId));
		hashMap.put("userCabinetChoices", userCabinetMap);

	}
}
