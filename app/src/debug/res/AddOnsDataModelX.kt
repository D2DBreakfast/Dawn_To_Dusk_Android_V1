data class AddOnsDataModelX(
    val addOnDetails: List<AddOnDetailX>,
    val message: String,
    val packageSize: List<PackageSizeX>,
    val status: Boolean,
    val statusCode: Int
)