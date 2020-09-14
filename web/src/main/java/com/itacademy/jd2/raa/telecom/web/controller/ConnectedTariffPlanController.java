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

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IConnectedTariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.dao.api.filter.ConnectedTariffPlanFilter;
import com.itacademy.jd2.raa.telecom.service.IConnectedTariffPlanService;
import com.itacademy.jd2.raa.telecom.service.ITariffPlanService;
import com.itacademy.jd2.raa.telecom.service.IUserCabinetService;
import com.itacademy.jd2.raa.telecom.web.converter.ConnectedTariffPlanFromDTOConverter;
import com.itacademy.jd2.raa.telecom.web.converter.ConnectedTariffPlanToDTOConverter;
import com.itacademy.jd2.raa.telecom.web.dto.ConnectedTariffPlanDTO;
import com.itacademy.jd2.raa.telecom.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/connectedTariffPlan")
public class ConnectedTariffPlanController extends AbstractController {
	@Autowired
	private IConnectedTariffPlanService connectedTariffPlanService;
	@Autowired
	private IUserCabinetService userCabinetService;
	@Autowired
	private ITariffPlanService tariffPlanService;
	@Autowired
	private ConnectedTariffPlanToDTOConverter toDtoConverter;
	@Autowired
	private ConnectedTariffPlanFromDTOConverter fromDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final ConnectedTariffPlanFilter filter = new ConnectedTariffPlanFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(connectedTariffPlanService.getCount(filter));

		filter.setFetchTariffPlan(true);
		final List<IConnectedTariffPlan> entities = connectedTariffPlanService.find(filter);
		List<ConnectedTariffPlanDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("connectedTariffPlan.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new ConnectedTariffPlanDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("connectedTariffPlan.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final ConnectedTariffPlanDTO formModel,
			final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("connectedTariffPlan.edit", hashMap);
		} else {
			final IConnectedTariffPlan entity = fromDtoConverter.apply(formModel);
			connectedTariffPlanService.save(entity);
			return "redirect:/connectedTariffPlan";
		}
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final ConnectedTariffPlanDTO dto = toDtoConverter.apply(connectedTariffPlanService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("connectedTariffPlan.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		connectedTariffPlanService.delete(id);
		return "redirect:/connectedTariffPlan";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IUserCabinet> userCabinet = userCabinetService.getAll();

		final Map<Integer, Integer> userCabinetMap = userCabinet.stream()
				.collect(Collectors.toMap(IUserCabinet::getId, IUserCabinet::getId));
		hashMap.put("userCabinetChoices", userCabinetMap);

		final List<ITariffPlan> tariffPlan = tariffPlanService.getAll();

		final Map<Integer, String> tariffPlanMap = tariffPlan.stream()
				.collect(Collectors.toMap(ITariffPlan::getId, ITariffPlan::getName));
		hashMap.put("tariffPlanChoices", tariffPlanMap);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IConnectedTariffPlan dbModel = connectedTariffPlanService.getFullInfo(id);
		final ConnectedTariffPlanDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("connectedTariffPlan.edit", hashMap);
	}
}
