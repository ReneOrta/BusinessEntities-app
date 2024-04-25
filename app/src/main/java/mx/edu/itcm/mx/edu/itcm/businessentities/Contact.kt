package mx.edu.itcm.mx.edu.itcm.businessentities

data class Contact(
    var be_id: BusinessEntities= BusinessEntities(),
    var p_id:  Person=Person(),
    var ct_id: ContactType= ContactType()
)
