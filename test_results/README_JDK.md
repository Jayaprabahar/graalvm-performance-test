# Application using OpenJDK 25

## 1. Build Image Size 
![screenshot](jdk_image_size.png)

## 2. Fetch 100000 records with concurrency 10 from MongoDB using GET API

```
oha -n 100000 -c 10 http://localhost:8080/api/employees
```

#### Progress
![screenshot](100000_No_Data_GET_Progress.png)
#### Results
![screenshot](100000_No_Data_GET_Results.png)


## 3. Insert 100000 records into MongoDB using POST API
```
oha -n 100000 -c 10 http://localhost:8080/api/employees/new -m POST
```

#### Progress
![screenshot](100000_Insert_POST_Progress_1.png)
![screenshot](100000_Insert_POST_Progress_2.png)

#### Results
![screenshot](100000_Insert_POST_Results.png)

## 4. Fetch 100000 records with concurrency 100 from MongoDB using GET API (After inserting 100000 records)
```
oha -n 100000 -c 100 http://localhost:8080/api/employees/
```

#### Progress without Index
![screenshot](100000_With_Data_GET_Progress_1.png)
![screenshot](100000_With_Data_GET_Progress_2.png)

#### Results without Index
![screenshot](100000_With_Data_GET_Results.png)
![screenshot](CPU_Hung.png)

## 5. Fetch 100000 records with concurrency 100 from MongoDB using GET API (After inserting 100000 records with index)

### Create Index on empId field in MongoDB 
![screenshot](Index_Created.png)

#### Progress with Index
![screenshot](100000_With_Data_GET_Progress_WithIndex_1.png)
![screenshot](100000_With_Data_GET_Progress_WithIndex_2.png)

#### Results with Index
![screenshot](100000_With_Data_GET_WithIndex_Results.png)