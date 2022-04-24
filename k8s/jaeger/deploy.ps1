# 1 deploy ingress
# https://kubernetes.github.io/ingress-nginx/deploy/#docker-desktop
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.2.0/deploy/static/provider/aws/deploy.yaml
echo "waiting for ingress to start..."
$ingressReadyPod = kubectl get pods -n ingress-nginx -l app.kubernetes.io/component=controller --output=jsonpath="{.items..metadata.name}"
kubectl wait --for=condition=ready -n ingress-nginx --timeout=240s pod/$ingressReadyPod
echo "`n`n"

# 2 deploy cert manager
# https://www.google.com/url?q=https://cert-manager.io/v1.6-docs/installation/%23default-static-install&sa=D&source=docs&ust=1650715640904159&usg=AOvVaw1vrwdDkbtZ7rps4a_E8Jz2
kubectl apply -f https://github.com/jetstack/cert-manager/releases/download/v1.6.3/cert-manager.yaml
echo "waiting for cert-manager to start..."
kubectl wait --for=condition=ready -n cert-manager --timeout=240s pod --all
echo "`n`n"
sleep(180);

# 3 Installing the Operator on Kubernetes
# https://www.jaegertracing.io/docs/1.33/operator/#installing-the-operator-on-kubernetes
kubectl create namespace observability
kubectl create -f https://github.com/jaegertracing/jaeger-operator/releases/download/v1.33.0/jaeger-operator.yaml -n observability
echo "waiting for operator to start..."
kubectl wait --for=condition=ready -n observability --timeout=240s pod --all
echo "`n`n"

# 4 deploy Jaeger all in one
# "Quick Start - Deploying the AllInOne image in https://www.jaegertracing.io/docs/1.33/operator/
# https://github.com/jaegertracing/jaeger-operator
kubectl apply -f jaeger-all-in-one.yaml -n observability
echo "waiting for all-in-one to start..."
sleep(5);
kubectl wait --for=condition=ready -n observability --timeout=240s pod --all
echo "`n`n"

# 5 The Jaeger UI is served via the Ingress
kubectl port-forward -n=observability service/simple-jaeger-query 16686:16686