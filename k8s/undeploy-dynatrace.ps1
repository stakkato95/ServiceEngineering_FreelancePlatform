# https://www.dynatrace.com/support/help/setup-and-configuration/setup-on-container-platforms/kubernetes/set-up-k8s-monitoring

kubectl delete -f https://github.com/Dynatrace/dynatrace-operator/releases/latest/download/kubernetes.yaml

kubectl delete dynakube --all -n dynatrace

kubectl -n dynatrace wait pod --for=delete -l dynatrace.com/component=operator --timeout=300s

kubectl delete -f https://github.com/Dynatrace/dynatrace-operator/releases/latest/download/kubernetes-csi.yaml
kubectl delete -f https://github.com/Dynatrace/dynatrace-operator/releases/latest/download/kubernetes.yaml
kubectl delete namespace dynatrace