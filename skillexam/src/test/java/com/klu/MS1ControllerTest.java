
package com.klu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(MS1Controller.class)
@ContextConfiguration(classes = SkillexamApplication.class)
public class MS1ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MS1Service service;

   
    @Test
    void testAddSuccess() throws Exception {

        when(service.add(5, 10)).thenReturn(15);

        mockMvc.perform(get("/add")
                .param("a", "5")
                .param("b", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("15"));
    }


    @Test
    void aatestMissingParameter() throws Exception {

        mockMvc.perform(get("/add")
                .param("a", "5"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

   
    @Test
    void testInvalidParameter() throws Exception {

        mockMvc.perform(get("/add")
                .param("a", "abc")
                .param("b", "10"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
