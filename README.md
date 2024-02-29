# Article Resources

## `ArticleResources` Class

### Endpoints

1. **Add Article**
   - **Endpoint:** `POST /articles`
   - **Description:** Adds a new article.
   - **Request Body:** ArticlesDto
   - **Response Type:** ResponseEntity<ArticlesDto>

2. **Edit Article**
   - **Endpoint:** `PATCH /articles`
   - **Description:** Edits an existing article.
   - **Request Body:** ArticlesDto
   - **Response Type:** ResponseEntity<ArticlesDto>
   - **Exception:** Throws `NotAllowedException` if editing is not allowed.

3. **Delete Article by ID**
   - **Endpoint:** `DELETE /articles/{id}`
   - **Description:** Deletes an article by its ID.
   - **Path Variable:** Integer (Article ID)
   - **Response Type:** ResponseEntity<Void>

4. **Get Article by ID**
   - **Endpoint:** `GET /articles/{id}`
   - **Description:** Retrieves an article by its ID.
   - **Path Variable:** Integer (Article ID)
   - **Response Type:** ResponseEntity<ArticlesDto>

5. **Get All Articles**
   - **Endpoint:** `GET /articles`
   - **Description:** Retrieves a paginated list of all articles.
   - **Query Parameters:**
     - `page` (default: 0) - Page number
     - `size` (default: 10) - Number of items per page
   - **Response Type:** ResponseEntity<Page<ArticlesDto>>

6. **Get User Feed Articles**
   - **Endpoint:** `GET /articles/feed`
   - **Description:** Retrieves a paginated list of articles from the user's feed.
   - **Query Parameters:**
     - `page` (default: 0) - Page number
     - `size` (default: 10) - Number of items per page
   - **Response Type:** ResponseEntity<Page<ArticlesDto>>

7. **Add Like to Article**
   - **Endpoint:** `POST /articles/{articleId}/like`
   - **Description:** Adds a like to the specified article.
   - **Path Variable:** Integer (Article ID)
   - **Response Type:** ResponseEntity<ArticlesDto>

8. **Delete Like from Article**
   - **Endpoint:** `DELETE /articles/{articleId}/like`
   - **Description:** Deletes a like from the specified article.
   - **Path Variable:** Integer (Article ID)
   - **Response Type:** ResponseEntity<ArticlesDto>

# Comments Resources

## `CommentsResources` Class

### Endpoints

1. **Add Comment to Article**
   - **Endpoint:** `POST /articles/{article_id}/comment`
   - **Description:** Adds a new comment to a specific article.
   - **Path Variable:** Integer (Article ID)
   - **Request Body:** CommentsDto
   - **Response Type:** ResponseEntity<CommentsDto>

2. **Get Comments by Article ID**
   - **Endpoint:** `GET /articles/{article_id}/comment`
   - **Description:** Retrieves all comments for a specific article.
   - **Path Variable:** Integer (Article ID)
   - **Response Type:** ResponseEntity<List<CommentsDto>>

# Image Controller

## `ImageController` Class

### Endpoints

1. **Upload Image**
   - **Endpoint:** `POST /image`
   - **Description:** Uploads an image.
   - **Request Parameter:** MultipartFile (file)
   - **Response Type:** ResponseEntity<?>

2. **Download Image by ID**
   - **Endpoint:** `GET /image/{id}`
   - **Description:** Downloads an image by its ID.
   - **Path Variable:** Integer (Image ID)
   - **Response Type:** void

# Tag Resources

## `TagResources` Class

### Endpoints

1. **Get Popular Tags**
   - **Endpoint:** `GET /tags`
   - **Description:** Retrieves a list of popular tags.
   - **Response Type:** ResponseEntity<List<String>>

# User Controller

## `UserController` Class

### Endpoints

1. **Add User (Sign-Up)**
   - **Endpoint:** `POST /user/sign-up`
   - **Description:** Adds a new user.
   - **Request Body:** UserDto
   - **Response Type:** ResponseEntity<String>

2. **Sign In**
   - **Endpoint:** `POST /user/sign-in`
   - **Description:** Authenticates a user.
   - **Request Parameters:**
     - `username` - User's username
     - `password` - User's password
   - **Response Type:** ResponseEntity<String>

3. **Get User by ID**
   - **Endpoint:** `GET /user/{id}`
   - **Description:** Retrieves a user by their ID.
   - **Path Variable:** Integer (User ID)
   - **Response Type:** ResponseEntity<UserDto>

4. **Update User**
   - **Endpoint:** `PATCH /user`
   - **Description:** Updates user information.
   - **Request Body:** UserDto
   - **Response Type:** ResponseEntity<UserDto>

5. **Follow User**
   - **Endpoint:** `POST /user/follow/{following}`
   - **Description:** Follows another user.
   - **Path Variable:** Integer (User ID to follow)
   - **Response Type:** ResponseEntity<FollowDto>

6. **Unfollow User**
   - **Endpoint:** `DELETE /user/follow/{following}`
   - **Description:** Unfollows another user.
   - **Path Variable:** Integer (User ID to unfollow)
   - **Response Type:** ResponseEntity<String>
