kubectl create -f .\postgres-deployment.yml
kubectl create -f .\postgres-service.yml

kubectl create -f .\platform-deployment.yml
kubectl create -f .\platform-service.yml