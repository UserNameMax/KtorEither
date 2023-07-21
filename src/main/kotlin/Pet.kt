data class Pet(
    val category: Category,
    val id: Long,
    val name: String,
    val photoUrls: List<String>,
    val status: String,
    val tags: List<Tag>
)