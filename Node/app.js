var express = require('express');
var repository = require('./repository.js');

var app = express();
app.use(express.json());

app.route('/')
        .get(async function (req, res) {
            res.json(await repository.findAll());
        })
        .post(async function (req, res) {
            var response = await repository.insertPost(req.body);
            res.json(response.ops[0]);
        });

app.route('/:postId')
        .get(async function (req, res) {
            res.json(await repository.findById(req.params.postId));
        })
        .put(async function (req, res) {
            await repository.updatePost(req.params.postId, req.body);
            res.json(req.body);
        })
        .delete(async function (req, res) {
            var reponse = await repository.deletePost(req.params.postId);
            res.status(204);
            res.json(reponse);
        });

app.post('/:postId/comments', async function (req, res) {
    await repository.addComment(req.params.postId, req.body);
    res.json(req.body);
});

app.delete('/:postId/comments/:commentId', async function (req, res) {
    var reponse = await repository.deleteComment(req.params.postId, req.params.commentId);
    res.status(204);
    res.json(reponse);
});

app.listen(8080);