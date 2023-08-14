apiVersion: apps/v1
kind: Deployment
metadata:
  name: caab-ebs-api
  namespace: laa-ccms-civil
  labels:
    app.kubernetes.io/name: caab-ebs-api
spec:
  replicas: 1
  selector:
    matchLabels:
      app.kubernetes.io/name: caab-ebs-api
  template:
    metadata:
      labels:
        app.kubernetes.io/name: caab-ebs-api
    spec:
      containers:
        - name: caab-ebs-api
          image: ${ECR_URL}:${IMAGE_TAG}
          ports:
            - containerPort: 8009

