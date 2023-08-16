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
          env:
            - name: CAAB_DATA_API_DATASOURCE_URL
              valueFrom:
                secretKeyRef:
                  name: saml-metadata-uri
                  key: caab-data-api-datasource-url
            - name: CAAB_DATA_API_DATASOURCE_USERNAME
              valueFrom:
                secretKeyRef:
                  name: saml-metadata-uri
                  key: caab-datasource-username
            - name: CAAB_DATA_API_DATASOURCE_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: saml-metadata-uri
                  key: caab-datasource-password
