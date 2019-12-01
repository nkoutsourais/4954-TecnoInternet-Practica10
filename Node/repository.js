var MongoClient = require('mongodb').MongoClient;
var ObjectId = require('mongodb').ObjectId;
var url = "mongodb://localhost:27017/";

var postsCollection;
var commentsCollection;

MongoClient.connect(url, {
    useUnifiedTopology: true,
    useNewUrlParser: true
}).then(async db => {
    postsCollection = db.db("foroDB").collection("postsCollection");
    commentsCollection = db.db("foroDB").collection("commentsCollection");
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
    var newComment = await commentsCollection.insertOne(comment);
    var queryNewComment = { $push: { comments: newComment.ops[0] } };
    await postsCollection.updateOne({ _id : new ObjectId(postId)}, queryNewComment);
}

async function deleteComment(postId, commentId) {
    await commentsCollection.deleteOne({ _id : new ObjectId(commentId)});
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