package com.entando.apiman.k8sserviceimpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.entando.apiman.entity.EntServiceDetail;
import com.entando.apiman.entity.WebClientEntity;
import com.entando.apiman.repositories.Repo;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.openapi.models.V1ServiceList;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Component

public class EntServiceDetailImpl implements com.entando.apiman.k8sService.EntServiceDetail

{
	public int len;
	public String nameSpace = "default";

	@Autowired
	K8ServiceConnector kConnector;
	@Autowired
	WebClient webClient;
	@Autowired
	private Repo repo;
	public List<String> ClusterIP1;

	public String ContextPath;

	public Integer Port;

	public List<String> fetchK8sServices(String ServiceType, String nameSpace)
			throws FileNotFoundException, IOException, ApiException {
		List<List<String>> clusterIP2 = new ArrayList<>();
		CoreV1Api api = kConnector.getV1API();
		List<String> address = new ArrayList<>();
		String ns = "entando";

		V1ServiceList list = api.listNamespacedService(nameSpace, "", null, null, null, null, null, null, null, null,
				null);

		list.getItems().forEach(e -> {
			try {
				V1PodList podList = api.listNamespacedPod(nameSpace, "pretty", null, null, null, null, null, null, null,
						null, null);
				ClusterIP1 = e.getSpec().getClusterIPs();
				try {

					clusterIP2.add(ClusterIP1);

				} catch (Exception g) {
					System.out.println("no");
				}
				e.getSpec().getPorts().stream().forEach(p -> {
					Port = p.getPort();

				});

				System.out.println(ClusterIP1 + ":" + Port);

				try {

					podList.getItems().forEach(l -> {
						l.getSpec().getContainers().forEach(cd -> {
							// Port = cd.getPorts().get(0).getHostPort().toString();
							cd.getEnv().forEach(ge -> {

								ContextPath = ge.getValue();

								address.add(ClusterIP1 + ":" + Port + "/" + ContextPath);

							});
						});
						;
					});
				} catch (Exception e2) {
					System.out.println("no value");
				}

			} catch (ApiException e1) {

				e1.printStackTrace();
			}
			;
		});
		Set<String> set = new HashSet<>(address);
		address.clear();
		address.addAll(set);
		len=address.size();
		return address;
	}

	@Override
	public void saveUrl() {
		EntServiceDetailImpl service = new EntServiceDetailImpl();

		System.out.println("inside of saveURL method");
		System.out.println("ContextPath=" + service.ContextPath);

		try {
			for (String f1 : this.fetchK8sServices("Internal", "entando")) {
				EntServiceDetail obj = new EntServiceDetail();

				System.out.println("URL ___________>" + f1);

				obj.setUrl(f1);

				System.out.println("inside of saveURL" + f1);
				repo.save(obj);
			}

		} catch (IOException | ApiException e) {
			System.out.println("inside of catch");
			e.printStackTrace();
		}
	}

	public Flux<WebClientEntity> getSubject() {
		return webClient.get().uri("/apiman/organizations/nv/apis/").retrieve().bodyToFlux(WebClientEntity.class);
	}

	public  Mono<WebClientEntity> create(WebClientEntity empl)
	{
	
		
		return webClient.post()
				.uri("/apiman/organizations/nv/apis/")
				.body(Mono.just(empl), WebClientEntity.class)
				.retrieve()
				.bodyToMono(WebClientEntity.class)
	        	.timeout(Duration.ofMillis(10_0000));

	}

}
