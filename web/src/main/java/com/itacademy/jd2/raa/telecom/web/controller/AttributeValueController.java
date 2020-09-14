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
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;
import com.itacademy.jd2.raa.telecom.dao.api.filter.AttributeValueFilter;
import com.itacademy.jd2.raa.telecom.service.IAttributeService;
import com.itacademy.jd2.raa.telecom.service.IAttributeValueService;
import com.itacademy.jd2.raa.telecom.web.converter.AttributeValueFromDTOConverter;
import com.itacademy.jd2.raa.telecom.web.converter.AttributeValueToDTOConverter;
import com.itacademy.jd2.raa.telecom.web.dto.AttributeValueDTO;
import com.itacademy.jd2.raa.telecom.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/attributeValue")
public class AttributeValueController extends AbstractController {
	@Autowired
	private IAttributeValueService attributeValueService;
	@Autowired
	private IAttributeService attributeService;
	@Autowired
	private AttributeValueToDTOConverter toDtoConverter;
	@Autowired
	private AttributeValueFromDTOConverter fromDtoConverter;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@PathVariable(name = "id", required = false) final Integer id,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final AttributeValueFilter filter = new AttributeValueFilter();
		
		prepareFilter(gridState, filter);
		gridState.setTotalCount(attributeValueService.getCount(filter));

		filter.setId(id);
		filter.setFetchAttribute(true);
		final List<IAttributeValue> entities = attributeValueService.find(filter);
		
		List<AttributeValueDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		
		models.put("gridItems", dtos);
		
		return new ModelAndView("attributeValue.list", models);
	}

	
	@RequestMapping(value = "/{id}/add", method = RequestMethod.GET)
	public ModelAndView showForm(@PathVariable(name = "id", required = true) final Integer id) {
		final Map<String, Object> hashMap = new HashMap<>();
		AttributeValueDTO attributeValueDTO = new AttributeValueDTO();
		
		attributeValueDTO.setAttributeId(id);
		
		hashMap.put("formModel", attributeValueDTO);
		
		loadCommonFormModels(hashMap);
		return new ModelAndView("attributeValue.edit", hashMap);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final AttributeValueDTO formModel,
			final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("attributeValue.edit", hashMap);
		} else {
			final IAttributeValue entity = fromDtoConverter.apply(formModel);
			attributeValueService.save(entity);
			return "redirect:/attributeValue/"+entity.getAttribute().getId();
		}
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		
		final AttributeValueDTO dto = toDtoConverter.apply(attributeValueService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("attributeValue.edit", hashMap);
	}

	@RequestMapping(value = "{attribute.id}/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "attribute.id", required = true) final Integer attributeId,
			@PathVariable(name = "id", required = true) final Integer id) {
		attributeValueService.delete(id);
		return "redirect:/attributeValue/"+attributeId;
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IAttribute> attributs = attributeService.getAll();

		final Map<Integer, String> attributsMap = attributs.stream()
				.collect(Collectors.toMap(IAttribute::getId, IAttribute::getName));
		hashMap.put("attributeChoices", attributsMap);

	}
}
