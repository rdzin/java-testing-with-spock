package com.manning.spock.chapter8.extras

import spock.lang.*

import com.manning.spock.chapter8.eshop.BillableBasket
import com.manning.spock.chapter8.eshop.CreditCardProcessor
import com.manning.spock.chapter8.eshop.Customer
import com.manning.spock.chapter8.eshop.Product

class AutoCloseSpec extends spock.lang.Specification{
	
	@AutoCleanup("shutdown")
	private CreditCardProcessor creditCardSevice = new CreditCardProcessor()
	
	def "credit card connection is closed down in the end"() {
		given: "a basket, a customer and a TV"
		Product tv = new Product(name:"bravia",price:1200,weight:18)
		BillableBasket basket = new BillableBasket()
		Customer customer = new Customer(name:"John",vip:false,creditCard:"testCard")

		and: "a credit card service"
		basket.setCreditCardProcessor(creditCardSevice)

		when: "user checks out the tv"
		basket.addProduct tv
		boolean success = basket.checkout(customer)

		then: "credit card is charged"
		success

	}
	
}

