# Practica 1 Tecnologias

## Documentación API

1. Listado de Posts

    ` method: ` **GET**

    ` url: ` **localhost:8080/api/posts/**

    ` code response: ` 200
    
    ` body response: `

    ```json
    [
        {
            "id": 2,
            "title": "nuevo post"
        },
        {
            "id": 3,
            "title": "nuevo post"
        }
    ]
    ```

2. Ver un Post

    ` method: ` **GET**

    ` url: ` **localhost:8080/api/posts/{postId}/**

    ` code response: ` 200
    
    ` body response: `

    ```json
    {
        "id": 3,
        "title": "nuevo post",
        "content": "prueba de nuevo post con contenido",
        "comments": [
            {
                "id": 4,
                "comment": "prueba de añadir comentario nuevo pati",
                "author": {
                    "id": 1,
                    "name": "Pati"
                }
            }
        ]
    }
    ```

3. Añadir un Post

    ` method: ` **POST**

    ` url: ` **localhost:8080/api/posts/**
    
    ` body send: `

    ```json
    {
        "title": "nuevo post",
        "content": "prueba de nuevo post con contenido"
    }
    ```

    ` code response: ` 201

    ` body response: `
    
    ```json
    {
        "id": 2,
        "title": "nuevo post",
        "content": "prueba de nuevo post con contenido",
        "comments": []
    }
    ```

4. Añadir un Comentario

    ` method: ` **POST**

    ` url: ` **localhost:8080/api/posts/{postId}/coments/**
    
    ` body send: `

    ```json
    {
        "author": {
            "id": 1
        },
        "comment": "prueba de añadir comentario nuevo pati"
    }
    ```
    ` code response: ` 201

    ` body response: `
    
    ```json
    {
        "id": 4,
        "comment": "prueba de añadir comentario nuevo pati",
        "author": {
            "id": 1,
            "name": null
        }
    }
    ```

5. Borrar un Comentario

    ` method: ` **DELETE**

    ` url: ` **localhost:8080/api/posts/{postId}/coments/{comentId}/**
    
    ` code response: ` 204
    

6. Añadir un Autor

    ` method: ` **POST**

    ` url: ` **localhost:8080/api/authors/**

    ` code response: ` 201

    ` body send: `

    ```json
    {
        "name": "Pati",
        "age": 18
    }
    ```   
    
    ` body response: `
    
    ```json
    {
        "id": 1,
        "name": "Pati",
        "age": 18
    }
    ```    

7. Ver comentarios de un autor

    ` method: ` **GET**

    ` url: ` **localhost:8080/api/authors/{authorId}/comments/**

    ` code response: ` 200
    
    ` body response: `

    ```json
    [
        {
            "id": 4,
            "comment": "prueba de añadir comentario nuevo pati",
            "post": {
                "id": 3
            }
        }
    ]
    ```