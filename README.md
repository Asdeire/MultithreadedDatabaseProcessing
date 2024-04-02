# Multithreaded Database Processing
## Objective
The objective of this practical work is to learn to utilize a multithreading approach in programming to handle large datasets efficiently.

## Tasks
Task 1: Data Generation
Generate a million records for a workers table in a database. The attributes of the table will become apparent in the following tasks. Utilize one of the following libraries for data generation:

Task 2: Parallel Processing
Parallelize the computations by dividing the database records into equal groups and processing each "data group" in a separate thread. Use the maximum number of threads available on the user's PC.

Task 3: Data Analysis
* Find the average salary.
* Find the highest and lowest salary among all workers.
* Count the number of employees with a certain level of education.
* Determine the number of male and female employees.

Task 4: Performance Comparison
Compare some tasks with their single-threaded version in terms of execution time.

Task 5: Parallel Streams Comparison
Compare your multithreading version with a version using parallelStream().


## Comparison and Analysis
Upon completion, provide a brief analysis comparing the performance of multithreaded processing, single-threaded processing, and parallel streams. Highlight the advantages and disadvantages of each approach based on execution time and resource utilization.

## Usage
- Clone the repository.
- Navigate to the project directory.
- Run the validation script.
- Follow the instructions provided to input data and observe the validation process.
## Contribution Guidelines
Contributions to the development of the Database Data Processing Service Layer system are welcome. Follow these steps to contribute:

1. Fork the repository.
2. Create a new branch for your feature or bug fix: `git checkout -b feature/new-feature`.
3. Make changes and commit them: `git commit -m "Add new feature"`.
4. Push changes to your fork: `git push origin feature/new-feature`.
5. Open a pull request.
## License
This project is licensed under the [MIT License](LICENSE).
