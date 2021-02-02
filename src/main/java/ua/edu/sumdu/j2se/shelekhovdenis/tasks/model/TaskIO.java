package ua.edu.sumdu.j2se.shelekhovdenis.tasks.model;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Objects;

/**
 * class for input/output of task list to/from file
 */
public class TaskIO {

    private static Logger logger = Logger.getLogger(TaskIO.class);

    /**
     * creates a stream for output
     * @param tasks - task list
     * @param out - output stream
     */
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

    /**
     * creates a stream for input
     * @param tasks - task list
     * @param in - input stream
     */
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

    /**
     * outputs stream to file
     * @param tasks - task list
     * @param file - file name and path
     */
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

    /**
     * input stream into file
     * @param tasks - task list
     * @param file - file name and path
     */
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

    /**
     * creates a stream for output(using json)
     * @param tasks - task list
     * @param out - writing stream
     */
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

    /**
     * creates a stream for input(using json)
     * @param tasks - task list
     * @param in - reading stream
     */
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

    /**
     * outputs stream to file(using json)
     * @param tasks - task list
     * @param file - file name and path
     */
    public static void writeText(AbstractTaskList tasks, File file){
        try(FileWriter writer = new FileWriter(file)){
            write(tasks, writer);
        } catch (IOException e) {
            logger.error("Tasks were not write(Text)", e);
            System.out.println("Tasks were not write(Text)");
        }
    }

    /**
     * input stream into file(using json)
     * @param tasks - task list
     * @param file - file name and path
     */
    public static void readText(AbstractTaskList tasks, File file){
        try(FileReader reader = new FileReader(file)){
            read(tasks, reader);
        } catch (IOException e) {
            logger.error("Tasks were not read(Text)", e);
            System.out.println("Tasks were not read(Text)");
        }
    }

}
