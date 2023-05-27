package stefanmonko.sk.accountsweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import stefanmonko.sk.accountsweb.FeatureProperties;
import stefanmonko.sk.accountsweb.IntegrationProperties;
import stefanmonko.sk.accountsweb.domain.Account;

@Controller
public class AccountsController {

	@Autowired
    private IntegrationProperties integrationProperties;

	@Autowired
    private FeatureProperties featureFlags;

	// if (featureFlags.getDatastream()){
	// 	return accountService.findById(id);
	// } else {
	// 	throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Feature not activated");
	// }

	@GetMapping("/accounts")
	public String accounts(Model model) {

		// String uri = integrationProperties.getBackend() + "/usersall";

		String uri;

		if (!featureFlags.getDatastream() && featureFlags.getDbdata()){
			uri = integrationProperties.getBackend() + "/usersall";
		} else if (featureFlags.getDatastream() && !featureFlags.getDbdata()){
			uri = integrationProperties.getBackend() + "/accounts";
		} else {
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Features misconfiguration");
		}

        RestTemplate restTemplate = new RestTemplate();     
        ResponseEntity<List<Account>> accountsResponse =
            restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
                });     
        List<Account> accounts = accountsResponse.getBody();

        System.out.println("Users: " + accounts);
		model.addAttribute("accounts", accounts);

		return "accounts";
	}

	@GetMapping("/account")
	public String account(@RequestParam(name="id", required=false, defaultValue="1") String id, Model model) {

		String uri;

		if (!featureFlags.getDatastream() && featureFlags.getDbdata()){
			uri = integrationProperties.getBackend() + "/userbyid/" + id;
		} else if (featureFlags.getDatastream() && !featureFlags.getDbdata()){
			uri = integrationProperties.getBackend() + "/accounts/" + id;;
		} else {
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Features misconfiguration");
		}


	    RestTemplate restTemplate = new RestTemplate();
	    
	    Account account = restTemplate.getForObject(uri, Account.class);
		model.addAttribute("id", account.getId());
		model.addAttribute("firstName", account.getFirstName());
		model.addAttribute("lastName", account.getLastName());
		model.addAttribute("email", account.getEmail());
		return "account";
	}

}

