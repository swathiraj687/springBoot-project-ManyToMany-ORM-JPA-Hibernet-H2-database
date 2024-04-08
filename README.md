In this project, we will build a Spring Boot application to serve as the backend for the 'Find My Project' application. The 'Find My Project' application forms the bridge between researchers and the a lot of projects they undertake. Users can look through the application to view the collaborations between researchers and the projects they're involved in.

For this application, we are focusing on two key entities: `Researcher` and `Project`. The `Researcher` entity establishes a Many-to-Many relationship with the `Project` entity.

<details>
<summary>**Implementation Files**</summary>

Use these files to complete the implementation:

- `ProjectController.java`
- `ProjectRepository.java`
- `ProjectJpaService.java`
- `ProjectJpaRepository.java`
- `Project.java`
- `ResearcherController.java`
- `ResearcherRepository.java`
- `ResearcherJpaService.java`
- `ResearcherJpaRepository.java`
- `Researcher.java`

</details>

Create a database that contains three tables `project`, `researcher`, and `researcher_project` using the given database schema.

You can refer to this [session](https://learning.ccbp.in/course?c_id=e345dfa4-f5ce-406e-b19a-4ed720c54136&s_id=6a60610e-79c2-4e15-b675-45ddbd9bbe82&t_id=f880166e-2f51-4403-81a0-d2430694dae8), for creating a database.

_Create the SQL files and compose accurate queries to run the application. Inaccurate SQL files will result in test case failures._

<details>
<summary>**Database Schema**</summary>

#### Project Table

| Columns |                 Type                  |
| :-----: | :-----------------------------------: |
|   id    | INTEGER (Primary Key, Auto Increment) |
|  name   |                 TEXT                  |
| budget  |                DOUBLE                 |

#### Researcher Table

|    Columns     |                 Type                  |
| :------------: | :-----------------------------------: |
|       id       | INTEGER (Primary Key, Auto Increment) |
|      name      |                 TEXT                  |
| specialization |                 TEXT                  |

#### Junction Table

|   Columns    |         Type          |
| :----------: | :-------------------: |
|  projectId   | INTEGER (Foreign Key) |
| researcherId | INTEGER (Foreign Key) |

The columns `projectId` and `researcherId` can be combinedly declared as Primary Keys.

You can use the given sample data to populate the tables.

<details>
<summary>**Sample Data**</summary>

#### Project Data

|  id   |     name      |  budget   |
| :---: | :-----------: | :-------: |
|   1   | Project Alpha | 50000.00  |
|   2   | Project Beta  | 100000.00 |
|   3   | Project Gamma | 150000.00 |
|   4   | Project Delta | 75000.00  |

#### Researcher Data

|  id   |      naem       |   specialization    |
| :---: | :-------------: | :-----------------: |
|   1   |   Marie Curie   |    Radioactivity    |
|   2   | Albert Einstein |     Relativity      |
|   3   |  Isaac Newton   | Classical Mechanics |
|   4   |   Niels Bohr    |  Quantum Mechanics  |

#### Junction Table

| researcherId | projectId |
| :----------: | :-------: |
|      1       |     1     |
|      1       |     2     |
|      2       |     2     |
|      3       |     3     |
|      3       |     4     |
|      4       |     4     |

</details>

</details>

<MultiLineNote>

Use only `project`, `researcher`, and `researcher_project` as the table names in your code.

</MultiLineNote>

### Completion Instructions

- `Project.java`: The `Project` class should contain the following attributes.

    |  Attribute  |       Type        |
    | :---------: | :---------------: |
    |  projectId  |        int        |
    | projectName |      String       |
    |   budget    |      double       |
    | researchers | List\<Researcher> |

- `ProjectRepository.java`: Create an `interface` containing the required methods.
- `ProjectJpaService.java`: Update the service class with logic for managing project data.
- `ProjectController.java`: Create the controller class to handle HTTP requests.
- `ProjectJpaRepository.java`: Create an interface that implements the `JpaRepository` interface.
  
- `Researcher.java`: The `Researcher` class should contain the following attributes.

    |   Attribute    |      Type      |
    | :------------: | :------------: |
    |  researcherId  |      int       |
    | researcherName |     String     |
    | specialization |     String     |
    |    projects    | List\<Project> |

- `ResearcherRepository.java`: Create an `interface` containing the required methods.
- `ResearcherJpaService.java`: Update the service class with logic for managing researcher data.
- `ResearcherController.java`: Create the controller class to handle HTTP requests.
- `ResearcherJpaRepository.java`: Create an interface that implements the `JpaRepository` interface.

Implement the following APIs.

<details>
<summary>**API 1: GET /researchers**</summary>

#### Path: `/researchers`

#### Method: `GET`

#### Description:

Returns a list of all researchers in the `researcher` table.

#### Response

```json
[
    {
        "researcherId": 1,
        "researcherName": "Marie Curie",
        "specialization": "Radioactivity",
        "projects": [
            {
                "projectId": 1,
                "projectName": "Project Alpha",
                "budget": 50000.0
            },
            {
                "projectId": 2,
                "projectName": "Project Beta",
                "budget": 100000.0
            }
        ]
    },
    ...
]
```

</details>

<details>
<summary>**API 2: POST /researchers**</summary>

#### Path: `/researchers`

#### Method: `POST`

#### Description:

Creates a new researcher in the `researcher` table. Also, create an association between the researcher and projects in the `researcher_project` table based on the `projectId`s provided in the `projects` field. The `researcherId` is auto-incremented.

#### Request

```json
{
    "researcherName": "Rosalind Franklin",
    "specialization": "Molecular Biology",
    "projects": [
        {
            "projectId": 4
        }
    ]
}
```

#### Response

```json
{
    "researcherId": 5,
    "researcherName": "Rosalind Franklin",
    "specialization": "Molecular Biology",
    "projects": [
        {
            "projectId": 4,
            "projectName": "Project Delta",
            "budget": 75000.0
        }
    ]
}
```

</details>

<details>
<summary>**API 3: GET /researchers/{researcherId}**</summary>

#### Path: `/researchers/{researcherId}`

#### Method: `GET`

#### Description:

Returns a researcher based on the `researcherId`. If the given `researcherId` is not found in the `researcher` table, raise `ResponseStatusException` with `HttpStatus.NOT_FOUND`.

#### Success Response

```json
{
    "researcherId": 1,
    "researcherName": "Marie Curie",
    "specialization": "Radioactivity",
    "projects": [
        {
            "projectId": 1,
            "projectName": "Project Alpha",
            "budget": 50000.0
        },
        {
            "projectId": 2,
            "projectName": "Project Beta",
            "budget": 100000.0
        }
    ]
}
```

</details>

<details>
<summary>**API 4: PUT /researchers/{researcherId}**</summary>

#### Path: `/researchers/{researcherId}`

#### Method: `PUT`

#### Description:

Updates the details of a researcher based on the `researcherId` and returns the updated researcher details. Also update the associations between the researcher and projects, if the `projects` field is provided. If the given `researcherId` is not found in the `researcher` table, raise `ResponseStatusException` with `HttpStatus.NOT_FOUND`.

#### Request

```json
{
    "projects": []
}
```

#### Success Response

```json
{
    "researcherId": 5,
    "researcherName": "Rosalind Franklin",
    "specialization": "Molecular Biology",
    "projects": []
}
```

</details>

<details>
<summary>**API 5: DELETE /researchers/{researcherId}**</summary>

#### Path: `/researchers/{researcherId}`

#### Method: `DELETE`

#### Description:

Deletes a researcher from the `researcher` table and its associations from the `researcher_project` table based on the `researcherId` and returns the status code `204`(raise `ResponseStatusException` with `HttpStatus.NO_CONTENT`). If the given `researcherId` is not found in the `researcher` table, raise `ResponseStatusException` with `HttpStatus.NOT_FOUND`.

</details>

<details>
<summary>**API 6: GET /researchers/{researcherId}/projects**</summary>

#### Path: `/researchers/{researcherId}/projects`

#### Method: `GET`

#### Description:

Returns all projects associated with the researcher based on the `researcherId`. If the given `researcherId` is not found in the `researcher` table, raise `ResponseStatusException` with `HttpStatus.NOT_FOUND`.

#### Success Response

```json
[
    {
        "projectId": 1,
        "projectName": "Project Alpha",
        "budget": 50000.0,
        "researchers": [
            {
                "researcherId": 1,
                "researcherName": "Marie Curie",
                "specialization": "Radioactivity"
            }
        ]
    },
    {
        "projectId": 2,
        "projectName": "Project Beta",
        "budget": 100000.0,
        "researchers": [
            {
                "researcherId": 1,
                "researcherName": "Marie Curie",
                "specialization": "Radioactivity"
            },
            {
                "researcherId": 2,
                "researcherName": "Albert Einstein",
                "specialization": "Relativity"
            }
        ]
    }
]
```

</details>

<details>
<summary>**API 7: GET /researchers/projects**</summary>

#### Path: `/researchers/projects`

#### Method: `GET`

#### Description:

Returns a list of all projects in the `project` table.

#### Response

```json
[
    {
        "projectId": 1,
        "projectName": "Project Alpha",
        "budget": 50000.0,
        "researchers": [
            {
                "researcherId": 1,
                "researcherName": "Marie Curie",
                "specialization": "Radioactivity"
            }
        ]
    },
    ...
]
```

</details>

<details>
<summary>**API 8: POST /researchers/projects**</summary>

#### Path: `/researchers/projects`

#### Method: `POST`

#### Description:

Creates a new project in the `project` table, if all the `researcherId`s in the `researchers` field exist in the `researcher` table. Also, create an association between the project and researchers in the `researcher_project` table. The `projectId` is auto-incremented. If any given `researcherId` is not found in the `researcher` table, raise `ResponseStatusException` with `HttpStatus.BAD_REQUEST`.

#### Request

```json
{
    "projectName": "DNA Structure Study",
    "budget": 120000.00,
    "researchers": [
        {
            "researcherId": 4
        }
    ]
}
```

#### Success Response

```json
{
    "projectId": 5,
    "projectName": "DNA Structure Study",
    "budget": 100000.0,
    "researchers": [
        {
            "researcherId": 4,
            "researcherName": "Niels Bohr",
            "specialization": "Quantum Mechanics"
        }
    ]
}
```

</details>

<details>
<summary>**API 9: GET /researchers/projects/{projectId}**</summary>

#### Path: `/researchers/projects/{projectId}`

#### Method: `GET`

#### Description:

Returns a project based on the `projectId`. If the given `projectId` is not found in the `project` table, raise `ResponseStatusException` with `HttpStatus.NOT_FOUND`.


#### Success Response

```json
{
    "projectId": 1,
    "projectName": "Project Alpha",
    "budget": 50000.0,
    "researchers": [
        {
            "researcherId": 1,
            "researcherName": "Marie Curie",
            "specialization": "Radioactivity"
        }
    ]
}
```

</details>

<details>
<summary>**API 10: PUT /researchers/projects/{projectId}**</summary>

#### Path: `/researchers/projects/{projectId}`

#### Method: `PUT`

#### Description:

Updates the details of a project based on the `projectId` and returns the updated project details. Also update the associations between the project and researchers, if the `researchers` field is provided. If the given `projectId` is not found in the `project` table, raise `ResponseStatusException` with `HttpStatus.NOT_FOUND`. If any given `researcherId` is not found in the `researcher` table, raise `ResponseStatusException` with `HttpStatus.BAD_REQUEST`.

#### Request

```json
{
    "budget": 120000.0,
    "researchers": [
        {
            "researcherId": 5
        }
    ]
}
```

#### Success Response

```json
{
    "projectId": 5,
    "projectName": "DNA Structure Study",
    "budget": 120000.0,
    "researchers": [
        {
            "researcherId": 5,
            "researcherName": "Rosalind Franklin",
            "specialization": "Molecular Biology"
        }
    ]
}
```

</details>

<details>
<summary>**API 11: DELETE /researchers/projects/{projectId}**</summary>

#### Path:  `/researchers/projects/{projectId}`

#### Method: `DELETE`

#### Description:

Deletes a project from the `project` table and its associations from the `researcher_project` table based on the `projectId` and returns the status code `204`(raise `ResponseStatusException` with `HttpStatus.NO_CONTENT`). If the given `projectId` is not found in the `project` table, raise `ResponseStatusException` with `HttpStatus.NOT_FOUND`.

</details>

<details>
<summary>**API 12: GET /projects/{projectId}/researchers**</summary>

#### Path: `/projects/{projectId}/researchers`

#### Method: `GET`

#### Description:

Returns all researchers associated with the project based on the `projectId`. If the given `projectId` is not found in the `project` table, raise `ResponseStatusException` with `HttpStatus.NOT_FOUND`.

#### Success Response

```json
[
    {
        "researcherId": 1,
        "researcherName": "Marie Curie",
        "specialization": "Radioactivity",
        "projects": [
            {
                "projectId": 1,
                "projectName": "Project Alpha",
                "budget": 50000.0
            },
            {
                "projectId": 2,
                "projectName": "Project Beta",
                "budget": 100000.0
            }
        ]
    }
]
```

</details>

**Do not modify the code in `FindMyProjectApplication.java`**