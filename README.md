# Movie Generator

This repository contains the code for a movie generator web application that allows users to generate random movie recommendations based on their preferences. The application consists of a backend deployed on Azure App Services and a frontend hosted on Azure App Services as well. Continuous integration and deployment (CI/CD) are managed using GitHub Actions for the backend and Azure DevOps Pipelines for the frontend.

## Table of Contents

- [Website URL](#website-url)
- [Features](#features)
- [Technologies](#technologies)
- [Local Development](#local-development)
- [Authors](#authors)
- [Contributions](#contributions)

## Website URL

You can access the live website at: [http://movie-generator.s3-website.eu-central-1.amazonaws.com/](http://movie-generator.s3-website.eu-central-1.amazonaws.com/)

## Features

- Users can choose movie preferences, including rating, release year, film length, genre, and desired cast.
- Clicking the "Generate" button will trigger the backend to generate a random movie recommendation based on the selected preferences.
- If users are not satisfied with the recommendation, they can click the "Regenerate" button to get a new recommendation or edit the parameters and generate again.

## Technologies

### Frontend Technologies:

- React.js: A popular JavaScript library for building interactive user interfaces.
- Material-UI: A set of React components and styles that follow Google's Material Design guidelines, enhancing the application's visual appeal.
- Axios: A JavaScript library used to make HTTP requests to the backend API, enabling smooth communication between the frontend and backend components.

### Backend Technologies:

- Spring Boot: A powerful framework for building Java applications, which was used to create the backend API.
- AWS Elastic Beanstalk: A service from Amazon Web Services that simplifies the deployment and scaling of web applications, hosting the backend of the movie generator.
- AWS Systems Manager (SSM): Used to securely fetch the required API key for the backend from the parameter store.

### Continuous Integration and Deployment (CI/CD) Technologies:

- GitHub Actions: GitHub's built-in CI/CD system is used for automating the continuous integration and continuous delivery processes for the backend.
- Azure DevOps Pipelines: Azure DevOps Pipelines are used to automate the build and deployment process for the frontend code.

## Local Development

To run the application locally for development, follow these steps:

### Backend

1. Open the backend project located in the `backend` directory using an IDE or code editor.
2. Configure Azure credentials to access Azure Key Vault.
3. Build and run the Spring Boot application.

### Frontend

1. Open the frontend project located in the `frontend` directory using an IDE or code editor.
2. Run `npm install` to install dependencies.
3. Run `npm start` to start the development server.

## Authors

This movie generator web application was collaboratively developed by the following contributors:

- [d3nnyyy](https://github.com/d3nnyyy): Responsible for backend development and deployment of the application.
- [N1tingale](https://github.com/N1tingale): Responsible for frontend development, designing the user-friendly interface.

## Contributions

Contributions to this repository are welcome. If you find any bugs or have suggestions for improvements, feel free to create a pull request or open an issue.

Happy movie watching! 🎬🍿
