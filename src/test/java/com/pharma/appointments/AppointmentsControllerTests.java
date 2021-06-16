package com.pharma.appointments;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pharma.appointments.config.JwtAuthenticationEntryPoint;
import com.pharma.appointments.config.JwtTokenUtil;
import com.pharma.appointments.config.WebSecurityConfiguration;
import com.pharma.appointments.controllers.AppointmentController;
import com.pharma.appointments.models.Appointment;
import com.pharma.appointments.models.AppointmentStatus;
import com.pharma.appointments.models.AppointmentType;
import com.pharma.appointments.models.ReasonType;
import com.pharma.appointments.repositories.AppointmentRepository;
import com.pharma.appointments.services.AppointmentService;
import com.pharma.appointments.services.AppointmentTypeService;
import com.pharma.appointments.services.ReasonTypeService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppointmentsApplication.class)
@WebMvcTest(AppointmentController.class)
class AppointmentsControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AppointmentTypeService appointmentTypeService;
    @MockBean
    private ReasonTypeService reasonTypeService;
    @MockBean
    private AmqpTemplate rabbitTemplate;
    @MockBean
    private AppointmentRepository appointmentRepository;
    @MockBean
    private AppointmentService appointmentService;
    @Autowired
    private WebSecurityConfiguration webSecurityConfiguration;
    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final Gson gson = new Gson();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void contextLoads() {
        assertThat(appointmentService).isNotNull();
        assertThat(webSecurityConfiguration).isNotNull();
        assertThat(jwtAuthenticationEntryPoint).isNotNull();
        assertThat(jwtTokenUtil).isNotNull();
    }

    @Test
    @WithMockUser(username = "admin", roles = "admin")
    public void getAllAppointmentsAPI()
            throws Exception {
        Appointment appointment1 = new Appointment();
        List<Appointment> allAppointments = new ArrayList<>();
        allAppointments.add(appointment1);

        given(appointmentService.getAllAppointments()).willReturn(allAppointments);
        mvc.perform(MockMvcRequestBuilders
                .get("/appointments/getall")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
    }

    @Test
    @WithMockUser(username = "admin", roles = "admin")
    public void getAllAppointmentByEmployeeIdAPI()
            throws Exception {
        Appointment appointment1 = new Appointment(1, new Date(), new Date(), new Date(), "reason", "attention", AppointmentStatus.ABSENT, "color","color", false, false, new AppointmentType(), new ReasonType(), 0,0,0);
        Appointment appointment2 = new Appointment(2, new Date(), new Date(), new Date(), "reason", "attention", AppointmentStatus.ABSENT, "color","color", false, false, new AppointmentType(), new ReasonType(), 0,0,0);
        List<Appointment> allAppointments = new ArrayList<>();
        allAppointments.add(appointment1);
        allAppointments.add(appointment2);
        String json = gson.toJson(allAppointments);
        allAppointments = gson.fromJson(json, new TypeToken<List<Appointment>>() {
        }.getType());
        given(appointmentService.getAllAppointmentsByEmployeeId(1)).willReturn(allAppointments);
        mvc.perform(MockMvcRequestBuilders
                .get("/appointments/employee-id/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists());
    }

    @Test
    @WithMockUser(username = "admin", roles = "admin")
    public void deleteAppointmentAPI()
            throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/appointments/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
