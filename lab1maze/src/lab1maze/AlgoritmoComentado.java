package lab1maze;

public class AlgoritmoComentado {
	
/*	closedset := the empty set   	---- Nodos evaluados
	openset:= {start}				---- Nodos vecinos para evaluar
	parent := the empty map g[start] := 0
	f[start] := g[start] + h(start) while openset is not empty


	current := the node in openset having the lowest f[] value if is_goal(current)
	return reconstruct_path(parent, current) remove current from openset
	add current to closedset
	for each neighbor in neighbor_nodes(current)
	if neighbor in closedset continue
	tentative_g := g[current] + dist_between(current,neighbor) if neighbor not in openset or tentative_g < g[neighbor]
	parent[neighbor] := current
	g[neighbor] := tentative_g
	f[neighbor] := g[neighbor] + h(neighbor) if neighbor not in openset
	add neighbor to openset
	return failure
	*/
}
