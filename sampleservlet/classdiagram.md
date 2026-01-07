---
config:
  theme: default
---
classDiagram
direction TB
    class User {
	    +String email
	    +String empId
	    +String password
	    +getter()
	    +setter()
    }

    class Offer {
	    +offerId
	    +offerName
	    +productType
	    +customerType
	    +minLoanAmount
	    +maxLoanAmount
	    +tenure
	    +interestRateType
	    +interestRate
	    +creditScore
	    +offerValidFrom
	    +offerValidTo
	    +getter()
	    +setter()
    }

    class UserDao {
	    +saveUser()
	    +getUser()
	    +deleteUserByEmpId()
    }

    class OfferDao {
	    +saveOffer()
	    +getOffer()
	    +deleteOffer()
	    +deleteOfferById()
	    +updateOffer()
    }

    class OfferService {
	    +offerUpload()
	    +getAllOffer()
	    +deleteOffe()
	    +updateOffer()
    }

    class UserService {
	    +createUser()
	    +valitateUser()
    }

    class LoginServlet {
	    +doPost()
	    +doGet()
	    +buildUser()
    }

    class LogoutServlet {
	    +doPost()
	    +doGet()
	    +buildUser()
    }

    class OfferServlet {
	    +doGet()
	    +doPost()
	    +doDelet()
	    +doPut()
    }

    class filter {
	    +dofilter()
    }

    class ConnectionProvider {
	    +closeConnection()
	    +getConnection()
    }

    OfferServlet -- OfferService
    OfferService -- OfferDao
    LoginServlet -- UserService
    UserService -- UserDao
    User -- Offer
    LogoutServlet -- UserService
    filter -- OfferServlet
    OfferDao -- ConnectionProvider
    UserDao -- ConnectionProvider
