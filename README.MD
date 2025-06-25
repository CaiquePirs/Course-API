# Challenge API  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="60" height="60" alt="Java"/>

## About the Challenge

In this challenge, you will develop a fictional API for a programming course company. At first, you must implement basic CRUD operations to manage courses.

The API should include the following features:
- Create a new course
- List all courses
- Update a course by its `id`
- Delete a course by its `id`

### Routes and Business Rules

Before diving into the routes, let’s understand the structure (properties) a course should have:

- `id` - Unique identifier for each course
- `name` - Course name
- `category` - Course category
- `active` - Indicates whether the course is active or not
- `created_at` - Date the course was created
- `updated_at` - Must always be updated with the date the course is modified

Routes:

- `POST - /courses`

  It should be possible to create a course in the database by sending the `name` and `category` fields in the request body.

  When creating a course, the fields `id`, `created_at`, and `updated_at` should be automatically filled according to the property guidelines above.

- `GET - /courses`

  It should be possible to list all courses saved in the database.

  It should also be possible to search courses by `name` and `category`.

- `PUT - /courses/:id`

  It should be possible to update a course by its `id`.

  In the request body, only `name` and/or `category` should be sent for updates.

  If only `name` is sent, `category` must not be updated, and vice versa. Also, if `active` is included in this route, it should be ignored. The route responsible for updating the active status is the PATCH route.

  It should be possible to delete a course by its `id`.

- `PATCH - /courses/:id/active`

  This route should be used to toggle the course's active status, i.e., a switch between true or false.
