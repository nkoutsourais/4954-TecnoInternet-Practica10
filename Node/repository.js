var MongoClient = require('mongodb').MongoClient;
var ObjectId = require('mongodb').ObjectId;
var url = "mongodb://localhost:27017/";

var postsCollection;

MongoClient.connect(url, {
    useUnifiedTopology: true,
    useNewUrlParser: true
}).then(async db => {
    postsCollection = db.db("foroDB").collection("postsCollection");
});

async function findAll() {
    return await postsCollection.find({}).toArray();
}

async function insertPost(post) {
    return await postsCollection.insertOne(post);
}

async function findById(postId) {
    return await postsCollection.find({ _id : new ObjectId(postId)}).toArray();
}

async function updatePost(postId, postUpdated) {
    var newValues = { $set: postUpdated };
    await postsCollection.updateOne({ _id : new ObjectId(postId)}, newValues);
}

async function deletePost(postId) {
    await postsCollection.deleteOne({ _id : new ObjectId(postId)});
}

async function addComment(postId, comment) {
    comment._id = new ObjectId();
    var queryNewComment = { $push: { comments: comment } };
    await postsCollection.updateOne({ _id : new ObjectId(postId)}, queryNewComment);
    return comment;
}

async function deleteComment(postId, commentId) {
    var delComment = { $pull: { comments: { _id: new ObjectId(commentId) } } };
    await postsCollection.updateOne({ _id : new ObjectId(postId)}, delComment);
}

module.exports.findAll = findAll
module.exports.findById = findById
module.exports.insertPost = insertPost
module.exports.updatePost = updatePost
module.exports.deletePost = deletePost
module.exports.addComment = addComment
module.exports.deleteComment = deleteComment