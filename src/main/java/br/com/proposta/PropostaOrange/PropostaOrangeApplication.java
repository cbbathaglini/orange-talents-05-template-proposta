package br.com.proposta.PropostaOrange;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;
import org.aspectj.weaver.tools.Trace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

@EnableFeignClients
@SpringBootApplication
public class PropostaOrangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaOrangeApplication.class, args);
	}

	/*

	@Bean
	public io.opentracing.Tracer jaegerTracer() {
		return new Configuration("propostas", new Configuration.SamplerConfiguration(ProbabilisticSampler.TYPE, 1),
				new Configuration.ReporterConfiguration())
				.getTracer();
	}

	private Object getTracer(){
		Tracer tracer = GlobalTracer.get();
		Tracer.SpanBuilder spanBuilder = tracer.buildSpan("Custom")
				.withTag(Tags.SPAN_KIND.getKey(),Tags.SPAN_KIND_SERVER);
	}

	 */
}