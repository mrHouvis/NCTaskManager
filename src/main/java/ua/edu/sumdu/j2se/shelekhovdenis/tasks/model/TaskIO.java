package ua.edu.sumdu.j2se.shelekhovdenis.tasks.model;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Objects;

public class TaskIO {

    private static Logger logger = Logger.getLogger(TaskIO.class);

    public static void write(AbstractTaskList tasks, OutputStream out){
        try(ObjectOutputStream writer = new ObjectOutputStream(out)){
            writer.writeInt(tasks.size());
            for (Task task : tasks) {
                writer.writeObject(task);
            }
        } catch (IOException e){
            logger.error("Tasks were not write", e);
            System.out.println("Tasks were not write");
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in){
        try(ObjectInputStream reader = new ObjectInputStream(in)){
            int size = reader.readInt();
            for (int i = 0; i < size; i++) {
                tasks.add((Task)reader.readObject());
            }
        } catch (ClassNotFoundException e){
            logger.error("Class is not found", e);
            System.out.println("Class is not found");
        } catch (IOException e){
            logger.error("Tasks were not read", e);
            System.out.println("Tasks were not read");
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file){
        try(FileOutputStream writer = new FileOutputStream(file)){
            write(tasks, writer);
        } catch (FileNotFoundException e) {
            logger.error("File is not found", e);
            System.out.println("File is not found");
        } catch (IOException e) {
            logger.error("Tasks were not write(Bin)", e);
            System.out.println("Tasks were not write(Bin)");
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file){
        try(FileInputStream reader = new FileInputStream(file)){
            read(tasks, reader);
        } catch (FileNotFoundException e) {
            logger.error("File is not found", e);
            System.out.println("File is not found");
        } catch (IOException e) {
            logger.error("Tasks were not read(Bin)", e);
            System.out.println("Tasks were not read(Bin)");
        }
    }

    public static void write(AbstractTaskList tasks, Writer out){
        try(BufferedWriter writer = new BufferedWriter(out)){
            Gson gson = new Gson();
            String tasksJson = gson.toJson(tasks);
            writer.write(tasksJson);
        } catch (IOException e) {
            logger.error("Tasks were not write(gson)", e);
            System.out.println("Tasks were not write(gson)");
        }
    }

    public static void read(AbstractTaskList tasks, Reader in){
        try(BufferedReader reader = new BufferedReader(in)){
            Gson gson = new Gson();
            String json = reader.readLine();
            AbstractTaskList tasksJson = gson.fromJson(json, Objects.requireNonNull(TaskListFactory.createTaskList(tasks.type)).getClass());
            for (Task task : tasksJson) {
                tasks.add(task);
            }
        } catch (IOException e) {
            logger.error("Tasks were not read(gson)", e);
            System.out.println("Tasks were not read(gson)");
        }
    }

    public static void writeText(AbstractTaskList tasks, File file){
        try(FileWriter writer = new FileWriter(file)){
            write(tasks, writer);
        } catch (IOException e) {
            logger.error("Tasks were not write(Text)", e);
            System.out.println("Tasks were not write(Text)");
        }
    }

    public static void readText(AbstractTaskList tasks, File file){
        try(FileReader reader = new FileReader(file)){
            read(tasks, reader);
        } catch (IOException e) {
            logger.error("Tasks were not read(Text)", e);
            System.out.println("Tasks were not read(Text)");
        }
    }

}
