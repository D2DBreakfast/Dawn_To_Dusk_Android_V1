data class UserRegistrationResponseModel(
    val RegisterData: RegisterData,
    val message: String,
    val status: Boolean,
    val statusCode: Int
)