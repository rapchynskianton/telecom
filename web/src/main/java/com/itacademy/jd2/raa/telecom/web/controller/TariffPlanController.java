package com.itacademy.jd2.raa.telecom.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttribute;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.filter.AttributeValueFilter;
import com.itacademy.jd2.raa.telecom.dao.api.filter.TariffPlanFilter;
import com.itacademy.jd2.raa.telecom.service.IAttributeService;
import com.itacademy.jd2.raa.telecom.service.IAttributeValueService;
import com.itacademy.jd2.raa.telecom.service.ITariffPlanService;
import com.itacademy.jd2.raa.telecom.web.converter.TariffPlanFromDTOConverter;
import com.itacademy.jd2.raa.telecom.web.converter.TariffPlanToDTOConverter;
import com.itacademy.jd2.raa.telecom.web.dto.TariffPlanDTO;
import com.itacademy.jd2.raa.telecom.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/tariffPlan")
public class TariffPlanController extends AbstractController {
	@Autowired
	private IAttributeValueService attributeValueService;

	@Autowired
	private IAttributeService attributeService;

	@Autowired
	private ITariffPlanService tariffPlanService;

	@Autowired
	private TariffPlanFromDTOConverter fromDtoConverter;

	@Autowired
	private TariffPlanToDTOConverter toDtoConverter;

	@RequestMapping(value = "/nameAttribute", method = RequestMethod.GET)
	public ResponseEntity<List<IAttribute>> getNameAttribute() {

		final List<IAttribute> entities = attributeService.getAll();
		return new ResponseEntity<List<IAttribute>>(entities, HttpStatus.OK);
	}

	@RequestMapping(value = "/valueAttribute", method = RequestMethod.GET)
	public ResponseEntity<List<IAttributeValue>> getValueAttribute(
			@RequestParam(name = "valueAttributeId", required = true) final Integer valueAttributeId) {

		final AttributeValueFilter filter = new AttributeValueFilter();
		filter.setId(valueAttributeId);
		filter.setFetchAttribute(true);
		final List<IAttributeValue> entities = attributeValueService.find(filter);
		return new ResponseEntity<List<IAttributeValue>>(entities, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final TariffPlanFilter filter = new TariffPlanFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(tariffPlanService.getCount(filter));

		final List<ITariffPlan> entities = tariffPlanService.find(filter);
		List<TariffPlanDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("tariffPlan.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		loadCommonFormAttributeValue(hashMap);
		hashMap.put("formModel", new TariffPlanDTO());
		return new ModelAndView("tariffPlan.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final TariffPlanDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormAttributeValue(hashMap);
			return new ModelAndView("tariffPlan.edit", hashMap);
		} else {
			final ITariffPlan entity = fromDtoConverter.apply(formModel);
			tariffPlanService.save(entity);
			return "redirect:/tariffPlan";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ITariffPlan dbModel = tariffPlanService.getFullInfo(id);
		final TariffPlanDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormAttributeValue(hashMap);
		hashMap.put("readonly", true);
		return new ModelAndView("tariffPlan.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final TariffPlanDTO dto = toDtoConverter.apply(tariffPlanService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		return new ModelAndView("tariffPlan.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		tariffPlanService.delete(id);
		return "redirect:/tariffPlan";
	}

	private void loadCommonFormAttributeValue(final Map<String, Object> hashMap) {

		final Map<Integer, Integer> attributeValueMap = attributeValueService.getAll().stream()
				.collect(Collectors.toMap(IAttributeValue::getId, IAttributeValue::getValue));
		hashMap.put("attributeValueChoices", attributeValueMap);

	}

}