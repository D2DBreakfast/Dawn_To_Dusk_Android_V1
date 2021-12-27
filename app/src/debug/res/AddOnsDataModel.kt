data class AddOnsDataModel(
    val addOnDetails: List<AddOnDetail>,
    val message: String,
    val packageSize: List<PackageSize>,
    val status: Boolean,
    val statusCode: Int
)