package mx.edu.itcm.mx.edu.itcm.businessentities.datasets

data class Customer(
    var cu_id: Int=0,
    var pe_id: Person = Person(),
    var str_id: Store = Store()
)
