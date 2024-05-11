package mx.edu.itcm.mx.edu.itcm.businessentities.navigation

 sealed class Destination(var route:String)
 {
     object HomeScreeen: Destination("home")
     object  PersonScreen: Destination("person")
     object StoreScreen: Destination("store")
     object VendorScreen: Destination("vendor")
 }