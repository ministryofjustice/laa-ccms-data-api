apiVersion: apps/v1
kind: Deployment
metadata:
  name: laa-ccms-civil-caab-data-api
spec:
  replicas: 1
  selector:
    matchLabels:
      app: laa-ccms-civil-caab-data-api-dev
  template:
    metadata:
      labels:
        app: laa-ccms-civil-caab-data-api-dev
    spec:
      containers:
        - name: laa-ccms-civil-caab-data-api
          image: ${ECR_URL}:${IMAGE_TAG}
          ports:
            - containerPort: 5000

