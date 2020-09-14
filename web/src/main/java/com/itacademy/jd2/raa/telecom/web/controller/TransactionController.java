package com.itacademy.jd2.raa.telecom.web.controller;

import java.math.BigDecimal;
import java.security.Principal;
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

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.dao.api.filter.TransactionFilter;
import com.itacademy.jd2.raa.telecom.service.ITransactionService;
import com.itacademy.jd2.raa.telecom.service.IUserCabinetService;
import com.itacademy.jd2.raa.telecom.web.converter.TransactionFromDTOConverter;
import com.itacademy.jd2.raa.telecom.web.converter.TransactionToDTOConverter;
import com.itacademy.jd2.raa.telecom.web.dto.TransactionDTO;
import com.itacademy.jd2.raa.telecom.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/transaction")
public class TransactionController extends AbstractController {
	@Autowired
	private ITransactionService transactionService;
	@Autowired
	private IUserCabinetService userCabinetService;
	@Autowired
	private TransactionToDTOConverter toDtoConverter;
	@Autowired
	private TransactionFromDTOConverter fromDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final TransactionFilter filter = new TransactionFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(transactionService.getCount(filter));

		filter.setFetchUserCabinet(true);
		final List<ITransaction> entities = transactionService.find(filter);
		List<TransactionDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("transaction.list", models);
	}

	@RequestMapping(value = "/{id}/add", method = RequestMethod.GET)
	public ModelAndView showForm(@PathVariable(name = "id", required = true) final Integer id) {

		final Map<String, Object> hashMap = new HashMap<>();
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setUserCabinetId(id);

		hashMap.put("formModel", transactionDTO);
		loadCommonFormModels(hashMap);
		return new ModelAndView("transaction.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final TransactionDTO formModel, Principal principal,
			final BindingResult result) {

		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("transaction.edit", hashMap);
		} else {
			final ITransaction entity = fromDtoConverter.apply(formModel);
			transactionService.save(entity);
			return "redirect:/userCabinet";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ITransaction dbModel = transactionService.getFullInfo(id);
		final TransactionDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("transaction.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final TransactionDTO dto = toDtoConverter.apply(transactionService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("transaction.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		transactionService.delete(id);
		return "redirect:/transaction";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IUserCabinet> userCabinets = userCabinetService.getAll();

		final Map<Integer, Integer> userCabinetMap = userCabinets.stream()
				.collect(Collectors.toMap(IUserCabinet::getId, IUserCabinet::getId));
		hashMap.put("userCabinetChoices", userCabinetMap);

	}
}
