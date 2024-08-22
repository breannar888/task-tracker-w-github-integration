//package com.tasktracker.tasktracker.service;
//
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.model.Filters;
//import com.tasktracker.tasktracker.models.Project;
//import com.tasktracker.tasktracker.repositories.ProjectRepo;
//import com.tasktracker.tasktracker.services.ProjectService;
//import org.bson.codecs.configuration.CodecRegistry;
//import org.junit.jupiter.api.BeforeEach;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static  org.assertj.core.api.Assertions.assertThat;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@SuppressWarnings("unchecked")
//public class ProjectServiceTest {
//
//    @Mock
//    private MongoClient mongoClient;
//
//    @Mock
//    private ProjectRepo projectRepo;
//
//    //should the service class be mocked?
//    private ProjectService projectService;
//
//    private final String dbName = "task-tracker-db";
//
//    @BeforeEach
//    public void init () {
//        projectService = new ProjectService(dbName, mongoClient, projectRepo);
//    }
//
//    @Test
//    public void when_projectServiceCallsGetProjectByName_thenReturnsOneProject() throws Exception {
//        MongoCollection<Project> mongoCollection = mock(MongoCollection.class);
//        FindIterable<Project> findIterable = mock(FindIterable.class);
//        MongoDatabase mongoDatabase = mock(MongoDatabase.class);
//
//        final String key = "name";
//        final String value = "Admin";
//
//        final String projectId = "65e3756386defd6bdaae3ad0";
//        final String userId = "65e3756386defd6bdaae3ad1";
//
//        Project mockProject = new Project();
//        mockProject.setId(projectId);
//        mockProject.setName(value);
//        mockProject.setUserId(userId);
//
//        when(mongoClient.getDatabase(eq(dbName))).thenReturn(mongoDatabase);
//        when(mongoDatabase.getCollection("Project", Project.class)).thenReturn(mongoCollection);
//        when(mongoCollection.withCodecRegistry(any(CodecRegistry.class))).thenReturn(mongoCollection);
//        when(mongoCollection.find(Filters.eq(key, value))).thenReturn(findIterable);
//        when(findIterable.first()).thenReturn(mockProject);
//
//        final Project project = projectService.getProjectByName(value);
//
//        assertThat(project)
//                .isNotNull()
//                .isEqualTo(mockProject);
//
//        verify(mongoClient, times(1)).getDatabase(eq(dbName));
//        verify(mongoCollection, times(1)).withCodecRegistry(any(CodecRegistry.class));
//        verify(mongoCollection, times(1)).find(Filters.eq(key, value));
//        verify(findIterable, times(1)).first();
//    }
//
////    @Test
////    public void when_projectServiceCallsGetProjectByProjectId_thenReturnsOneProject() throws Exception {
////        MongoClient mongoClient1 = mock(MongoClient.class);
////        MongoClientFactoryBean mongoClientFactoryBean = mock(MongoClientFactoryBean.class);
////        MongoCollection<Project> mongoCollection = mock(MongoCollection.class);
////        FindIterable<Project> findIterable = mock(FindIterable.class);
////        MongoDatabase mongoDatabase = mock(MongoDatabase.class);
////
////        final String key = "_id";
////        final String id = "65e3756386defd6bdaae3ad0";
////
////        final String userId = "65e3756386defd6bdaae3ad1";
////
////        Project mockProject = new Project();
////        mockProject.setProjectID(id);
////        mockProject.setName("Admin");
////        mockProject.setUserId(userId);
////
////        when(mongoClient1.mongoClient()).thenReturn(mongoClientFactoryBean);
////        when(mongoClientFactoryBean.getObject()).thenReturn(mongoClient);
////        when(mongoClient1.getDatabase(eq(dbName))).thenReturn(mongoDatabase);
////        when(mongoDatabase.getCollection("Project", Project.class)).thenReturn(mongoCollection);
////        when(mongoCollection.withCodecRegistry(any(CodecRegistry.class))).thenReturn(mongoCollection);
////        when(mongoCollection.find(Filters.eq(key, id))).thenReturn(findIterable);
////        when(findIterable.first()).thenReturn(mockProject);
////
////        final Project project = projectService.getProjectByProjectId(id);
////
////        assertThat(project)
////                .isNotNull()
////                .isEqualTo(mockProject);
////
////        verify(mongoClient, times(1)).mongoClient();
////        verify(mongoClient, times(1)).getDatabase(eq(dbName));
////        verify(mongoCollection, times(1)).withCodecRegistry(any(CodecRegistry.class));
////        verify(mongoCollection, times(1)).find(Filters.eq(key, id));
////        verify(findIterable, times(1)).first();
////    }
//////failing test case needs further work
////    @Test
////    public void when_projectServiceCallsGetProjectsByUserId_thenReturnsListOfProjects() throws Exception {
////        MongoClient mongoClient1 = mock(MongoClient.class);
////        MongoClientFactoryBean mongoClientFactoryBean = mock(MongoClientFactoryBean.class);
////        MongoCollection<Project> mongoCollection = mock(MongoCollection.class);
////        FindIterable findIterable = mock(FindIterable.class);
////        MongoDatabase mongoDatabase = mock(MongoDatabase.class);
////
////        final String key = "userId";
////        final String userID = "65bfc109b2a9736ff1eecfc7";
////        final String name1 = "Admin";
////        final String name2 = "Test1";
////
////        Project project1 = new Project();
////        project1.setProjectID("65bfc0f1b2a9736ff1eecfc4");
////        project1.setName(name1);
////        project1.setUserId(userID);
////
////        Project project2 = new Project();
////        project2.setProjectID("65df85ed46ee2e892bf55514");
////        project2.setName(name2);
////        project2.setUserId(userID);
////
////        List<Project> projects = new ArrayList<>();
////        projects.add(project1);
////        projects.add(project2);
////
////        when(mongoClient1.mongoClient()).thenReturn(mongoClientFactoryBean);
////        when(mongoClientFactoryBean.getObject()).thenReturn(mongoClient);
////        when(mongoClient1.getDatabase(eq(dbName))).thenReturn(mongoDatabase);
////        when(mongoDatabase.getCollection("Project", Project.class)).thenReturn(mongoCollection);
////        when(mongoCollection.withCodecRegistry(any(CodecRegistry.class))).thenReturn(mongoCollection);
////        when(mongoCollection.find(Filters.eq(key, userID))).thenReturn(findIterable);
////        when(findIterable.into(new ArrayList<>())).thenReturn(projects);
////
////        List<Project> result = projectService.getProjectsByUserId(userID);
////
////        assertThat(result)
////                .isNotNull()
////                .isEqualTo(projects);
////
////        System.out.println("result: " + result + " / projects: " + projects);
////
////        verify(mongoClient, times(1)).mongoClient();
////        verify(mongoClient, times(1)).getDatabase(eq(dbName));
////        verify(mongoCollection, times(1)).withCodecRegistry(any(CodecRegistry.class));
////        verify(mongoCollection, times(1)).find(Filters.eq(key, userID));
////        verify(findIterable, times(1)).into(any(List.class));
////    }
//
//}
