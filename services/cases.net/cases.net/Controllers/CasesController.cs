using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.OData;
using Org.Naima.Services.Cases.Models;
using System.Threading.Tasks;

namespace cases.net.Controllers
{
    public class CasesController : ODataController
    {
        CasesContext db = new CasesContext();

        protected override void Dispose(bool disposing)
        {
            db.Dispose();
            base.Dispose(disposing);
        }

        private bool CaseExists(int key)
        {
            return db.Cases.Any(p => p.Id == key);
        }
        [EnableQuery]
        public IQueryable<Case> Get()
        {
            return db.Cases;
        }
      
        [EnableQuery]
        public SingleResult<Patient> GetPatient([FromODataUri]int key)
        {
            var result = db.Cases.Where(l => l.Id == key).Select(c => c.Patient);
            return SingleResult.Create<Patient>(result);
        }
        [EnableQuery]
        public SingleResult<Doctor> GetDoctor([FromODataUri]int key)
        {
            var result = db.Cases.Where(l => l.Id == key).Select(c => c.Doctor);
            return SingleResult.Create<Doctor>(result);
        }
        public SingleResult<CareProvider> GetCareProvider([FromODataUri]int key)
        {
            var result = db.Cases.Where(l => l.Id == key).Select(c => c.CareProvider);
            return SingleResult.Create<CareProvider>(result);
        }
        public async Task<IHttpActionResult> Post(Case cs)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            db.Cases.Add(cs);
            await db.SaveChangesAsync();
            return Created(cs);
        }
    }
}
