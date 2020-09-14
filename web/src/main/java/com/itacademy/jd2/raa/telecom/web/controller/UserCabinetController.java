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
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.dao.api.filter.UserCabinetFilter;
import com.itacademy.jd2.raa.telecom.service.IBranchService;
import com.itacademy.jd2.raa.telecom.service.IUserAccountService;
import com.itacademy.jd2.raa.telecom.service.IUserCabinetService;
import com.itacademy.jd2.raa.telecom.web.converter.UserCabinetFromDTOConverter;
import com.itacademy.jd2.raa.telecom.web.converter.UserCabinetToDTOConverter;
import com.itacademy.jd2.raa.telecom.web.dto.UserCabinetDTO;
import com.itacademy.jd2.raa.telecom.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.raa.telecom.web.security.AuthHelper;

@Controller
@RequestMapping(value = "/userCabinet")
public class UserCabinetController extends AbstractController {
	@Autowired
	private IUserCabinetService userCabinetService;
	@Autowired
	private IUserAccountService userAccountService;
	@Autowired
	private IBranchService branchService;
	@Autowired
	private UserCabinetToDTOConverter toDtoConverter;
	@Autowired
	private UserCabinetFromDTOConverter fromDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {
		
		Integer loggedUserId = AuthHelper.getLoggedUserId();
		
		
		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final UserCabinetFilter filter = new UserCabinetFilter();
		String role = userAccountService.get(loggedUserId).getRole().name();
		if (role=="user")
		{
			filter.setId(loggedUserId);
		}
		
		prepareFilter(gridState, filter);
		gridState.setTotalCount(userCabinetService.getCount(filter));

		filter.setFetchUserAccount(true);
		filter.setFetchBranch(true);
		
		final List<IUserCabinet> entities = userCabinetService.find(filter);
		List<UserCabinetDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		
//		Integer userCabinetId = entities.get(0).getId();
//		filter.setIdUserCabinet(userCabinetId);
//		Integer sum = userCabinetService.getSum(userCabinetId); 
//		if (userCabinetId!=null) {
//		Integer ii =userCabinetService.getSum(userCabinetId);
//			
//		}
		
		
		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("userCabinet.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new UserCabinetDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("userCabinet.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final UserCabinetDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("userCabinet.edit", hashMap);
		} else {
			final IUserCabinet entity = fromDtoConverter.apply(formModel);
			userCabinetService.save(entity);
			return "redirect:/userCabinet";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IUserCabinet dbModel = userCabinetService.getFullInfo(id);
		final UserCabinetDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("userCabinet.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final UserCabinetDTO dto = toDtoConverter.apply(userCabinetService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("userCabinet.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		userCabinetService.delete(id);
		return "redirect:/userCabinet";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IUserAccount> userAccount = userAccountService.getAll();

		final Map<Integer, String> userAccountMap = userAccount.stream()
				.collect(Collectors.toMap(IUserAccount::getId, IUserAccount::getEmail));
		hashMap.put("userAccountChoices", userAccountMap);

		final List<IBranch> branch = branchService.getAll();

		final Map<Integer, String> branchMap = branch.stream()
				.collect(Collectors.toMap(IBranch::getId, IBranch::getRegion));
		hashMap.put("branchChoices", branchMap);

	}

}
