package mx.unam.dgtic.diplomado.proyecto.modelo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.DerivadaModeloMatematico;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.ModeloMatematico;

@Repository
public interface IDerivadaModeloMatematicoRepository extends CrudRepository<DerivadaModeloMatematico, Integer> {
	public abstract void deleteByModeloMatematico(ModeloMatematico modeloMatematico);
}
