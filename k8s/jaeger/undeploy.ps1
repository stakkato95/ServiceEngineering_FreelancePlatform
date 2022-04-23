# 4 deploy Jaeger all in one
# "Quick Start - Deploying the AllInOne image in https://www.jaegertracing.io/docs/1.33/operator/
# https://github.com/jaegertracing/jaeger-operator
kubectl delete -f jaeger-all-in-one.yaml -n observability

# 3 Installing the Operator on Kubernetes
# https://www.jaegertracing.io/docs/1.33/operator/#installing-the-operator-on-kubernetes

kubectl delete -f https://github.com/jaegertracing/jaeger-operator/releases/download/v1.33.0/jaeger-operator.yaml -n observability
kubectl delete namespace observability

# 2 deploy cert manager
# https://www.google.com/url?q=https://cert-manager.io/v1.6-docs/installation/%23default-static-install&sa=D&source=docs&ust=1650715640904159&usg=AOvVaw1vrwdDkbtZ7rps4a_E8Jz2
kubectl delete -f https://github.com/jetstack/cert-manager/releases/download/v1.6.3/cert-manager.yaml

# 1 deploy ingress
# https://kubernetes.github.io/ingress-nginx/deploy/#docker-desktop
kubectl delete -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.2.0/deploy/static/provider/aws/deploy.yaml
sleep(240);