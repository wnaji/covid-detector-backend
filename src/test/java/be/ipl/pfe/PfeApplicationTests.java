package be.ipl.pfe;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PfeApplication.class)
class PfeApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    public void test1() {
        System.out.println("test1 works!!!");
    }

}
