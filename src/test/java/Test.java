
import com.spring.mvc.portfolio.repository.AssetRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Test {
    @org.junit.Test
    public void t1() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("springdata-jpa-config.xml");
        AssetRepository ar = ctx.getBean(AssetRepository.class);
        System.out.println(ar.findBy());
        ctx.close();
    }
}
