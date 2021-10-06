# camel-dynamic-scheduler
A sample project on how to utilize Apache camel to create a dynamic scheduler based on the given cron attributes.

## How to Run the service
1. Clone the project
2. `cd` into the project directory
3. Run the service by typing `./mvnw spring-boot:run`

## Testing the service

There are two endpoints available for you to create the scheduler. 

1. Default Scheduler

```
curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' localhost:8080/api/schedulers/default/{your_message}
```

E.g. 

```
curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' localhost:8080/api/schedulers/default/Heavy+Process
```

2. Custom Scheduler

```
curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{"seconds": "[0-59]", "minutes": "[0-59]", "hours": "[0-23]", "dayOfMonth": "[1-31]", "months": "[1-12]", "dayOfWeek": "[1-7]", "message": "<your message>"}' localhost:8080/api/schedulers
```

E.g. To create a scheduler that will be running for every 7 seconds:

```
curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{"seconds": "7", "message": "Heavy Processes"}' localhost:8080/api/schedulers
```
