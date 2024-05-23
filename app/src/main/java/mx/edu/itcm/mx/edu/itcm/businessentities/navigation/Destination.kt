package mx.edu.itcm.mx.edu.itcm.businessentities.navigation

 sealed class Destination(var route:String)
 {
     object HomeScreeen: Destination("home")
     object  PersonRegistrationScreen: Destination("personRegistration")
     object  PersonSearchScreen:Destination("personSearch")
     object StoreRegistrationScreen: Destination("storeRegistration")
     object StoreSearchScreen:Destination("storeSearch")
     object VendorRegistrationScreen: Destination("vendorRegistration")
     object VendorSearchScreen:Destination("vendorSearch")
 }