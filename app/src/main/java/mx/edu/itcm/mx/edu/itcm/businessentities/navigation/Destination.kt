package mx.edu.itcm.mx.edu.itcm.businessentities.navigation

 sealed class Destination(var route:String)
 {
     object HomeScreeen: Destination("home")
     object  PersonRegistrationScreen: Destination("personRegistration")
     object  PersonSearchScreen:Destination("personSearch")
     object  PersonUpdateScreen:Destination("personUpdate")
     object StoreRegistrationScreen: Destination("storeRegistration")
     object StoreSearchScreen:Destination("storeSearch")
     object StoreUpdateScreen:Destination("storeUpdateScreen")
     object VendorRegistrationScreen: Destination("vendorRegistration")
     object VendorSearchScreen:Destination("vendorSearch")
     object VendorUpdateScreen:Destination("vendorUpdate")

 }
