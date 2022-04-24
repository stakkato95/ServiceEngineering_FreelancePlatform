kubectl delete -f .\postgres-deployment.yml
kubectl delete -f .\postgres-service.yml

kubectl delete -f .\platform-deployment.yml
kubectl delete -f .\platform-service.yml