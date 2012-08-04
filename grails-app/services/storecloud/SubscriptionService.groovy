package storecloud

import org.springframework.transaction.annotation.Transactional;

@Transactional
class SubscriptionService {

	def accountService
	def shopService

	def createRegistration(String email, String domain) {


		ShoppyAccount.withTransaction {
			statusAccount -> accountService.createAccount(email)

		}

		Shop.withTransaction {
			statusShop -> shopService.createShop(domain, ShoppyAccount.findByEmail(email))
		}
		
		def url = "http://"+domain+".storeapp.com:8080"
		def message = "<h1>Your shop is ready!</h1><br/>visit this link to access it: <a href=\"${url}\">${url}</a>"
		
		try {
			sendMail {
				to      "${email}"
				subject "Your e-shop is available"
				html    message
			}
		} catch(Exception e) {
			log.error "Problem sending email $e.message", e
		}
	}
}
