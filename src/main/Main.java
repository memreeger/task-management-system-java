package main;

import model.*;
import service.TaskManagementSystem;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        TaskManagementSystem system = new TaskManagementSystem();

        System.out.println("========== FULL SYSTEM TEST ==========\n");

        // ======================
        // 1. USERS
        // ======================
        User emre = system.createUser("Emre", "emre@gmail.com");
        User cemre = system.createUser("Cemre", "cemre@gmail.com");

        System.out.println("Users:");
        System.out.println(emre);
        System.out.println(cemre);
        System.out.println();

        // ======================
        // 2. PROJECT
        // ======================
        Project project = system.createProject("OXXO Task App", "Internal task tracking system");

        System.out.println("Project:");
        System.out.println(project);
        System.out.println();

        // ======================
        // 3. ADD USERS TO PROJECT
        // ======================
        system.addUserToProject(emre.getId(), project.getId());
        system.addUserToProject(cemre.getId(), project.getId());

        System.out.println("Members:");
        for (User u : project.getMembers()) {
            System.out.println(u.getName());
        }
        System.out.println();

        // ======================
        // 4. CREATE TASKS
        // ======================
        Task task1 = system.createTask(
                project.getId(),
                "Login Page",
                "Design login UI",
                Priority.HIGH,
                LocalDateTime.now().plusDays(2)
        );

        Task task2 = system.createTask(
                project.getId(),
                "Database Setup",
                "Create schema",
                Priority.MEDIUM,
                LocalDateTime.now().plusDays(5)
        );

        System.out.println("Tasks Created:");
        System.out.println(task1);
        System.out.println(task2);
        System.out.println();

        // ======================
        // 5. ASSIGN TASKS
        // ======================
        system.assignUserToTask(emre.getId(), task1.getId(), project.getId());
        system.assignUserToTask(cemre.getId(), task2.getId(), project.getId());

        System.out.println("After Assignment:");
        System.out.println(task1);
        System.out.println(task2);
        System.out.println();

        // ======================
        // 6. STATUS UPDATE
        // ======================
        task1.changeStatus(TaskStatus.IN_PROGRESS);
        task2.changeStatus(TaskStatus.DONE);

        System.out.println("Status Update:");
        System.out.println(task1);
        System.out.println(task2);
        System.out.println();

        // ======================
        // 7. COMMENTS
        // ======================
        task1.addComment(new Comment("UI draft ready", emre));
        task2.addComment(new Comment("DB completed", cemre));

        System.out.println("Comments:");
        task1.getComments().forEach(System.out::println);
        task2.getComments().forEach(System.out::println);
        System.out.println();

        // ======================
        // 8. QUERY TESTS
        // ======================
        System.out.println("Tasks by STATUS (DONE):");
        project.getTasksByStatus(TaskStatus.DONE).forEach(System.out::println);
        System.out.println();

        System.out.println("Tasks by ASSIGNEE (Emre):");
        project.getTasksByAssignee(emre).forEach(System.out::println);
        System.out.println();

        System.out.println("Tasks by TITLE (Login Page):");
        project.getTasksByTitle("Login Page").forEach(System.out::println);
        System.out.println();

        // ======================
        // 9. OVERDUE CHECK
        // ======================
        System.out.println("Overdue Check:");
        System.out.println(task1.getTitle() + " -> " + task1.isOverdue());
        System.out.println(task2.getTitle() + " -> " + task2.isOverdue());
        System.out.println();

        // ======================
        // 10. REMOVE OPERATIONS
        // ======================
        System.out.println("Removing Task 1...");
        boolean removedTask = project.removeTask(task1.getId());
        System.out.println("Removed: " + removedTask);

        System.out.println("Remaining Tasks:");
        project.getTasks().values().forEach(System.out::println);
        System.out.println();

        System.out.println("Removing User Cemre from Project...");
        boolean removedUser = system.removeUserFromProject(cemre.getId(), project.getId());
        System.out.println("Removed: " + removedUser);

        System.out.println("Remaining Members:");
        project.getMembers().forEach(u -> System.out.println(u.getName()));
        System.out.println();

        // ======================
        // 11. EDGE CASE TEST
        // ======================
        System.out.println("Edge Case Test:");

        try {
            system.assignUserToTask("wrongId", task2.getId(), project.getId());
        } catch (Exception e) {
            System.out.println("Error caught: " + e.getMessage());
        }

        try {
            system.createUser("Test", "emre@gmail.com");
        } catch (Exception e) {
            System.out.println("Duplicate user error: " + e.getMessage());
        }

        System.out.println();

        System.out.println("========== TEST FINISHED ==========");
    }
}