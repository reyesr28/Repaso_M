package Beans

class Post {
    var userId:Int
    var id:Int
    var title:String
    var body:String

    constructor(userId: Int, id: Int, title: String, body: String) {
        this.userId = userId
        this.id = id
        this.title = title
        this.body = body
    }
}