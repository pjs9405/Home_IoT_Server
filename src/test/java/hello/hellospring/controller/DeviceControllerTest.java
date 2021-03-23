package hello.hellospring.controller;


import hello.hellospring.SpringConfig;
import hello.hellospring.domain.Device;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({DeviceController.class, Device.class, SpringConfig.class})
public class DeviceControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void 테스트() throws Exception {
        mvc.perform(get("/device/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"deviceCount\":3")
                ));
    }
}
