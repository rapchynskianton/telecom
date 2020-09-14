package com.itacademy.jd2.raa.telecom.web.controller;

import java.util.Arrays;
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

import com.itacademy.jd2.raa.telecom.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.raa.telecom.service.IUserAccountService;
import com.itacademy.jd2.raa.telecom.web.converter.UserAccountFromDTOConverter;
import com.itacademy.jd2.raa.telecom.web.converter.UserAccountToDTOConverter;
import com.itacademy.jd2.raa.telecom.web.dto.UserAccountDTO;
import com.itacademy.jd2.raa.telecom.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.raa.telecom.web.dto.search.UserAccountSearchDTO;

@Controller
@RequestMapping(value = "/userAccount")
public class UserAccoutController extends AbstractController {
	private static final String SEARCH_FORM_MODEL = "searchFormModel";
	private static final String SEARCH_DTO = UserAccoutController.class.getSimpleName() + "_SEACH_DTO";

	@Autowired
	private IUserAccountService userAccountService;

	@Autowired
	private UserAccountFromDTOConverter fromDtoConverter;

	@Autowired
	private UserAccountToDTOConverter toDtoConverter;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView index(final HttpServletRequest req,
			@ModelAttribute(SEARCH_FORM_MODEL) UserAccountSearchDTO searchDto,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		if (req.getMethod().equalsIgnoreCase("get")) {
			searchDto = getSearchDTO(req);
		} else {
			req.getSession().setAttribute(SEARCH_DTO, searchDto);
		}

		final UserAccountFilter filter = new UserAccountFilter();

		if (searchDto.getEmail() != null) {
			filter.setEmail(searchDto.getEmail());
		}

		prepareFilter(gridState, filter);

		final List<IUserAccount> entities = userAccountService.find(filter);
		List<UserAccountDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(userAccountService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);

		models.put(SEARCH_FORM_MODEL, searchDto);

		final IUserAccount searchUserAccount = userAccountService.getSearchUserAccount();
		models.put("newestUserAccountId", searchUserAccount == null ? null : searchUserAccount.getId());
		return new ModelAndView("userAccount.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new UserAccountDTO());
		loadComboboxesModels(hashMap);
		return new ModelAndView("userAccount.edit", hashMap);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final UserAccountDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadComboboxesModels(hashMap);
			return new ModelAndView("userAccount.edit", hashMap);
		} else {
			final IUserAccount entity = fromDtoConverter.apply(formModel);
			userAccountService.save(entity);
			return "redirect:/userAccount";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IUserAccount dbModel = userAccountService.getFullInfo(id);
		final UserAccountDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadComboboxesModels(hashMap);
		return new ModelAndView("userAccount.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final UserAccountDTO dto = toDtoConverter.apply(userAccountService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadComboboxesModels(hashMap);
		return new ModelAndView("userAccount.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		userAccountService.delete(id);
		return "redirect:/userAccount";
	}

	private void loadComboboxesModels(final Map<String, Object> hashMap) {

		final List<UserRole> userRoleList = Arrays.asList(UserRole.values());
		final Map<String, String> userRoleMap = userRoleList.stream()
				.collect(Collectors.toMap(UserRole::name, UserRole::name));

		hashMap.put("roleChoices", userRoleMap);

	}

	@RequestMapping(value = "/lastId", method = RequestMethod.GET)
	public ResponseEntity<Integer> getSearchUserAccount() {
		final IUserAccount userAccount = userAccountService.getSearchUserAccount();
		return new ResponseEntity<Integer>(userAccount == null ? null : userAccount.getId(), HttpStatus.OK);
	}

	private UserAccountSearchDTO getSearchDTO(final HttpServletRequest req) {
		UserAccountSearchDTO searchDTO = (UserAccountSearchDTO) req.getSession().getAttribute(SEARCH_DTO);
		if (searchDTO == null) {
			searchDTO = new UserAccountSearchDTO();
			req.getSession().setAttribute(SEARCH_DTO, searchDTO);
		}
		return searchDTO;
	}

}
