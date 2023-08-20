import java.util.*;

public class ProjectRecord {
    private List<Project> pr;

    public ProjectRecord() {
        pr = new ArrayList<Project>();
    }//constructor

    public void addProject(Project p) {
        pr.add(p);
    }

    public int getNumberOfProjects() {
        return pr.size();
    }

    public String findAll(String arg, String value) {
        ListIterator<Project> iter = pr.listIterator();
        StringBuilder result = new StringBuilder();

        while (iter.hasNext()) {
            Project current = iter.next();
            if (arg.equals("location") && current.getLocation().equalsIgnoreCase(value)) {
                if (result.toString().equals("No projects found")) {
                    result = new StringBuilder();
                }
                result.append(current.getProject());

            } else if (arg.equals("type") && current.getType().equalsIgnoreCase(value)) {
                if (result.toString().equals("No projects found")) {
                    result = new StringBuilder();
                }
                result.append(current.getProject());
            } else if (arg.equals("cost") && current.getCost() <= Double.parseDouble(value)) {
                if (result.toString().equals("No projects found")) {
                    result = new StringBuilder();
                }
                result.append(current.getProject());
            }
        }
        if (value == null || value.trim().isEmpty()) {
            result.append("Please enter a valid project " + arg + ".\n");
        } else if (result.toString().isEmpty()) {
            result.append("No projects found");
        }

        return result.toString();
    }//find all

    public String removeProject(int id, String name) {
        ListIterator<Project> iter = pr.listIterator();
        StringBuilder result = new StringBuilder("No projects found");

        while (iter.hasNext()) {
            Project current = iter.next();
            if (current.getId() == id && current.getName().equalsIgnoreCase(name)) {
                iter.remove();
                result.append("Project removed.");
            }
        }
        if (name == null || name.trim().isEmpty() || String.valueOf(id).isEmpty()) {
            result.append("Project not found for removal...");
        }
        if (result.toString().equals("No projects found")) {
            result = new StringBuilder("No project found for removal..");
        }
        return result.toString();
    }//remove project
    public String checkDuplicates(int id, String name) {
        ListIterator<Project> iter = pr.listIterator();
        StringBuilder result = new StringBuilder("No projects found");
        while (iter.hasNext()) {
            Project current = iter.next();
            if (current.getId() == id && current.getName().equalsIgnoreCase(name)) {
                if (result.toString().equals("No projects found")) {
                    result = new StringBuilder();
                }
                result.append(current.getProject());
            }
        }
        return result.toString();
    }//check duplicate
}
